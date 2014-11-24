package dungeoncrawler.ui;

import dungeoncrawler.logic.GameHandler;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
