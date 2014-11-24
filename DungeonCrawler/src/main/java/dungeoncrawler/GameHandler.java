package dungeoncrawler;

import dungeoncrawler.map.Map;
import dungeoncrawler.gameobject.creature.Player;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;

/**
 * This class contains the logic for running the game loop at the highest level.
 * The class also functions as a hub which contains all the major game components.
 */
public class GameHandler {

    private Map map;
    private Player player;
    private UserInterface ui;
    private boolean gameHasEnded;
    private ActionHandler actionHandler;

    public GameHandler() {
        player = new Player();
        map = new Map(player);
        ui = new UserInterface(this);
        gameHasEnded = false;
        actionHandler = new ActionHandler(this);
    }

    public Map getMap() {
        return map;
    }

    public void run() {
        SwingUtilities.invokeLater(ui);
    }

    public void handleGameEnd() {
        gameHasEnded = true;
        System.out.println("Game over!");
    }

    public boolean gameHasEnded() {
        return gameHasEnded;
    }

    public void handleMovement(KeyEvent e) {
        if (gameHasEnded) {
            return;
        }
        if (!actionHandler.handlePlayerMove(e)) {
            return;
        }
        actionHandler.handleNPCActions();
        ui.update();
    }

    public Player getPlayer() {
        return player;
    }

}
