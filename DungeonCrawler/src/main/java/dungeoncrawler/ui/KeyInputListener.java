package dungeoncrawler.ui;

import dungeoncrawler.logic.GameHandler;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class implements the KeyListener interface to allow the player to
 * interact with the game.
 */

public class KeyInputListener implements KeyListener {

    private GameHandler gameHandler;

    public KeyInputListener(GameHandler gh) {
        this.gameHandler = gh;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gameHandler.handleMovement(e);
    }

}
