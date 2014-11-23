package dungeoncrawler;

import dungeoncrawler.gameobject.GameObject;
import dungeoncrawler.gameobject.creature.Creature;
import dungeoncrawler.map.Map;
import dungeoncrawler.gameobject.creature.Player;
import dungeoncrawler.map.Square;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;

/**
 * This class contains the logic for running the game loop at the highest level.
 */
public class GameHandler {

    private Map map;
    private Player player;
    private UserInterface ui;

    public GameHandler() {
        player = new Player();
        map = new Map(player);
        ui = new UserInterface(map, this);
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
        if (targetObject != null) {
            if (!targetObject.isWalkable()) {
                return;
            }
        }
        moveCreature(targetSquare, player);
        ui.update();
    }
    
    private void moveCreature(Square targetSquare, Creature creature) {
        creature.getSquare().setCreatureOnSquare(null);
        targetSquare.setCreatureOnSquare(creature);
        player.setSquare(targetSquare);
    }

}
