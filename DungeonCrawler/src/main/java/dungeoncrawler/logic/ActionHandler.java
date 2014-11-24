package dungeoncrawler.logic;

import dungeoncrawler.gameobject.creature.Creature;
import dungeoncrawler.gameobject.creature.NPC;
import dungeoncrawler.gameobject.creature.Player;
import dungeoncrawler.map.Map;
import dungeoncrawler.map.Square;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class handles the logic for actions such as movement and combat. The
 * class implements combat and movement logic both for NPCs and the player.
 */
public class ActionHandler {

    private MapUtils movementUtils;
    private Map map;
    private Player player;
    private GameHandler gameHandler;
    private Random rng;

    public ActionHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.map = gameHandler.getMap();
        this.movementUtils = new MapUtils(map);
        this.player = gameHandler.getPlayer();
        this.rng = new Random();
    }

    /**
     * This method attempts to handle the player's move action which happens
     * when the player presses a key. The player will attempt to move to an
     * adjacent square depending on which arrow key was pressed. If the square
     * contains another creature, the player will attack it. If it contains no
     * objects which block movement, the player will move to the square. If it
     * contains an object which blocks movement, or if the target square is
     * outside map boundaries, no action will be taken. In case an action was
     * taken, true is returned, otherwise false is returned.
     *
     * @param e the key code matching the key pressed by the player
     * @return true if an action was taken, false if not
     */
    public boolean handlePlayerMove(int e) {
        int playerX = player.getSquare().getX();
        int playerY = player.getSquare().getY();
        int dX = 0;
        int dY = 0;
        if (e == KeyEvent.VK_LEFT) {
            dX -= 1;
        } else if (e == KeyEvent.VK_RIGHT) {
            dX += 1;
        } else if (e == KeyEvent.VK_UP) {
            dY -= 1;
        } else if (e == KeyEvent.VK_DOWN) {
            dY += 1;
        }
        if (playerX + dX < 0 || playerX + dX > map.getWidth() - 1 || playerY + dY < 0 || playerY + dY > map.getHeight() - 1) {
            return false;
        }
        Square targetSquare = map.getMap()[playerX + dX][playerY + dY];
        Creature targetCreature = targetSquare.getCreatureOnSquare();
        if (!movementUtils.squareIsMovable(targetSquare) && targetCreature == null) {
            return false;
        }
        if (targetCreature != null) {
            attackCreature(player, targetCreature);
        }
        if (targetCreature == null) {
            moveCreature(player, targetSquare);
        }
        return true;
    }

    /**
     * This method handles actions for all NPCs on the map. All non-hostile NPCs
     * will move if a random value is higher than their chance to move randomly.
     * All hostile NPCs will attempt to attack the player in case the player is
     * within the hostile NPC's aggro range, otherwise they will act similarly
     * to non-hostile creatures. Each NPC executes exactly one action, and in
     * case no actions are available will pass their turn.
     *
     */
    public void handleNPCActions() {
        List<NPC> NPCs = new ArrayList<NPC>();
        for (Square[] row : map.getMap()) {
            for (Square square : row) {
                Creature creature = square.getCreatureOnSquare();
                if (creature != null) {
                    if (!creature.isPlayer()) {
                        NPCs.add((NPC) creature);
                    }
                }
            }
        }
        for (NPC entity : NPCs) {
            if (!gameHandler.gameHasEnded()) {
                if (!entity.isHostile() || entity.getAggroRadius() < movementUtils.getDistanceToTarget(entity.getSquare(), player.getSquare())) {
                    if (rng.nextDouble() < entity.getChanceToMoveRandomly()) {
                        moveToRandomAdjacentSquare(entity);
                    }
                } else {
                    attemptToAttackPlayer(entity);
                }
            }

        }
    }

    /**
     * This method causes the specified creature to attempt to attack the
     * player. If the player is in a non-diagonally adjacent square, an attack
     * will be performed. Otherwise, the creature will attempt to move towards
     * the player.
     *
     * @param creature The creature which will attempt the attack
     */
    private void attemptToAttackPlayer(Creature creature) {
        if (movementUtils.getDistanceToTarget(creature.getSquare(), player.getSquare()) == 1) {
            attackCreature(creature, player);
            return;
        }
        List<Square> possibleSquares = movementUtils.getMovableAdjacentSquares(creature.getSquare());
        if (possibleSquares.isEmpty()) {
            return;
        }
        moveCreature(creature, movementUtils.findSquareNearestToTarget(possibleSquares, player.getSquare()));
    }

    /**
     * This method will cause the specified creature to attack the specified
     * target. Damage equal to the attacker's damage modifier will be caused
     * to the target. 
     * 
     * @param attacker The creature performing the attack
     * @param target The target of the attack
     */
    private void attackCreature(Creature attacker, Creature target) {
        target.takeDamage(attacker.getDamage());
        if (target.getCurrentHealth() == 0) {
            target.getSquare().setCreatureOnSquare(null);
            target.setSquare(null);
            if (target.isPlayer()) {
                gameHandler.handleGameEnd();
            }
        }
    }

    /**
     * This method will move the specified creature to the specified square.
     * The previous square the creature was on will be emptied, the new square
     * will receive a reference to the creature and the creature will receive
     * a reference to the new square.
     * 
     * @param creature The creature to be moved
     * @param targetSquare The square to move to
     */
    private void moveCreature(Creature creature, Square targetSquare) {
        creature.getSquare().setCreatureOnSquare(null);
        targetSquare.setCreatureOnSquare(creature);
        creature.setSquare(targetSquare);
    }

    /**
     * This method will attempt to move the specified creature to a random
     * adjacent square. If no movable adjacent squares are found, the creature
     * will not move. 
     * 
     * @param creature The creature attempting to move
     */
    private void moveToRandomAdjacentSquare(Creature creature) {
        Square targetSquare = movementUtils.pickRandomAdjacentMovableSquare(creature.getSquare());
        if (targetSquare != null) {
            moveCreature(creature, targetSquare);
        }
    }

}
