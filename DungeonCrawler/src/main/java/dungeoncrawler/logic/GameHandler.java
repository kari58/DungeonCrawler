package dungeoncrawler.logic;

import dungeoncrawler.ui.UserInterface;
import dungeoncrawler.map.Map;
import dungeoncrawler.gameobject.creature.Player;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;

/**
 * The purpose of this class is to function as a hub which links together all
 * game elements. All other game objects should be accessible through this
 * class. It also implements high-level logic for running the game on each turn.
 * The class does not implement specific actions, but directs the use of other
 * classes for each turn.
 *
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

    /**
     * Returns true if the game has ended. Can be used to check if input should
     * still be listened to.
     *
     * @return true if game has ended, false if not
     */
    public boolean gameHasEnded() {
        return gameHasEnded;
    }

    /**
     * Requests the Map to update for a new level and asks UI to re-draw. Should
     * be called once whenever entering a new map.
     */
    public void enterNewLevel() {
        map.updateMapForNewLevel(player);
        ui.update();
    }

    /**
     * This method should be called whenever the player presses a key. No action
     * should be taken if the game has ended. If the player presses Enter to use
     * stairs, he will be moved through the stairs if possible, but no NPC
     * action is taken. If the player makes a valid directional move, NPCs will
     * also take their turn. If no valid action is taken, NPCs will not take
     * action either.
     *
     * @param e The KeyEvent corresponding to the button pressed by the player
     */
    public void handleMovement(int e) {
        if (gameHasEnded) {
            return;
        }
        int[] validMoves = {KeyEvent.VK_ENTER, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
        boolean moveIsValid = false;
        for (int i = 0; i < validMoves.length; i++) {
            if (validMoves[i] == e) {
                moveIsValid = true;
            }
        }
        if (!moveIsValid) {
            return;
        }
        if (e == KeyEvent.VK_ENTER) {
            if (actionHandler.useStairs()) {
                enterNewLevel();
                return;
            } else {
                return;
            }
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
