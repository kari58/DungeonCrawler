package dungeoncrawler.logic;

import dungeoncrawler.logic.ActionHandler;
import dungeoncrawler.ui.UserInterface;
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

    /**
     * This method is called when the game ends. To be implemented.
     */
    public void handleGameEnd() {
        gameHasEnded = true;
    }

    public boolean gameHasEnded() {
        return gameHasEnded;
    }

    /**
     * This method is called whenever the player presses a key. If the game has
     * not ended, a request to handle player action is passed to the Action
     * Handler. If a player action was taken, the Action Handler is requested
     * to handle actions for all NPCs, and the UI is updated.
     * @param e The KeyEvent corresponding to the button pressed by the player
     */
    public void handleMovement(int e) {
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
    
    public ActionHandler getActionHandler() {
        return actionHandler;
    }

}
