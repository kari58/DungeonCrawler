package dungeoncrawler;

import dungeoncrawler.gameobject.creature.Creature;
import dungeoncrawler.gameobject.creature.NPC;
import dungeoncrawler.gameobject.creature.Player;
import dungeoncrawler.map.Map;
import dungeoncrawler.map.Square;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ActionHandler {

    private MovementUtils movementUtils;
    private Map map;
    private Player player;
    private GameHandler gameHandler;

    public ActionHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.map = gameHandler.getMap();
        this.movementUtils = new MovementUtils(map);
        this.player = gameHandler.getPlayer();
    }

    public boolean handlePlayerMove(KeyEvent e) {
        int playerX = player.getSquare().getX();
        int playerY = player.getSquare().getY();
        int dX = 0;
        int dY = 0;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            dX -= 1;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dX += 1;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            dY -= 1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
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
        for (NPC creature : NPCs) {
            if (!gameHandler.gameHasEnded()) {
                if (!creature.isHostile() || creature.getAggroRadius() < movementUtils.getDistanceToTarget(creature.getSquare(), player.getSquare())) {
                    moveToRandomSquare(creature);
                } else {
                    attemptToAttackPlayer(creature);
                }
            }

        }
    }

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

    private void moveCreature(Creature creature, Square targetSquare) {
        creature.getSquare().setCreatureOnSquare(null);
        targetSquare.setCreatureOnSquare(creature);
        creature.setSquare(targetSquare);
    }

    private void moveToRandomSquare(Creature creature) {
        Square targetSquare = movementUtils.pickRandomSquareToMoveTo(creature.getSquare());
        if (targetSquare != null) {
            moveCreature(creature, targetSquare);
        }
    }

}
