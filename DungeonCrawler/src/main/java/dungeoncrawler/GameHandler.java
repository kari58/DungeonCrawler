package dungeoncrawler;

import dungeoncrawler.gameobject.GameObject;
import dungeoncrawler.gameobject.creature.Creature;
import dungeoncrawler.gameobject.creature.NPC;
import dungeoncrawler.map.Map;
import dungeoncrawler.gameobject.creature.Player;
import dungeoncrawler.map.Square;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.SwingUtilities;

/**
 * This class contains the logic for running the game loop at the highest level.
 */
public class GameHandler {

    private Map map;
    private Player player;
    private UserInterface ui;
    private Random rng;

    public GameHandler() {
        player = new Player();
        map = new Map(player);
        ui = new UserInterface(map, this);
        rng = new Random();
    }

    public void run() {
        SwingUtilities.invokeLater(ui);
    }

    private void handleGameEnd() {
        System.out.println("Game over!");
    }

    public void handleMovement(KeyEvent e) {
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
            return;
        }
        Square targetSquare = map.getMap()[playerX + dX][playerY + dY];
        Creature targetCreature = targetSquare.getCreatureOnSquare();
        GameObject targetObject = targetSquare.getObjectOnSquare();
        if (targetCreature != null) {
            // attack and return, rest of the stuff assumes creature is null
            return;
        }
        if (!squareIsMovable(targetSquare)) {
            return;
        }
        moveCreature(targetSquare, player);
        moveNPCs();
        ui.update();
    }

    private void moveCreature(Square targetSquare, Creature creature) {
        creature.getSquare().setCreatureOnSquare(null);
        targetSquare.setCreatureOnSquare(creature);
        creature.setSquare(targetSquare);
    }

    private void moveNPCs() {
        List<Creature> NPCs = new ArrayList<Creature>();
        for (Square[] row : map.getMap()) {
            for (Square square : row) {
                Creature creature = square.getCreatureOnSquare();
                if (creature != null) {
                    if (!creature.isPlayer()) {
                        System.out.println("Added creature " + creature.getName());
                        NPCs.add(creature);
                    }
                }
            }
        }
        for (Creature creature : NPCs) {
            Square targetSquare = pickRandomSquareToMoveTo(creature.getSquare());
            if (targetSquare != null) {
                moveCreature(targetSquare, creature);
            }

        }
    }

    private List<Square> getMovableAdjacentSquares(Square originalSquare) {
        int squareX = originalSquare.getX();
        int squareY = originalSquare.getY();
        List<Square> squares = new ArrayList<Square>();
        if (squareX - 1 >= 0) {
            if (squareIsMovable(map.getMap()[squareX - 1][squareY])) {
                squares.add(map.getMap()[squareX - 1][squareY]);
            }
        }
        if (squareX + 1 < map.getWidth()) {
            if (squareIsMovable(map.getMap()[squareX + 1][squareY])) {
                squares.add(map.getMap()[squareX + 1][squareY]);
            }
        }
        if (squareY - 1 >= 0) {
            if (squareIsMovable(map.getMap()[squareX][squareY - 1])) {
                squares.add(map.getMap()[squareX][squareY - 1]);
            }
        }
        if (squareY + 1 < map.getHeight()) {
            if (squareIsMovable(map.getMap()[squareX][squareY + 1])) {
                squares.add(map.getMap()[squareX][squareY + 1]);
            }
        }
        return squares;
    }

    private boolean squareIsMovable(Square square) {
        if (square.getCreatureOnSquare() != null) {
            return false;
        }
        if (square.getObjectOnSquare() != null) {
            if (!square.getObjectOnSquare().isWalkable()) {
                return false;
            }
        }
        return true;
    }

    private Square pickRandomSquareToMoveTo(Square originalSquare) {
        List<Square> squares = getMovableAdjacentSquares(originalSquare);
        if (squares.isEmpty()) {
            return null;
        }
        return squares.get(rng.nextInt(squares.size()));
    }

}
