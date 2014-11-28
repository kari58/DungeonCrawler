package dungeoncrawler.ui;

import dungeoncrawler.logic.GameHandler;
import dungeoncrawler.map.Map;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * This class encapsulates the JFrame containing the UI of the game.
 * @author Keke
 */

public class UserInterface implements Runnable {

    private JFrame frame;
    private Map map;
    private GameHandler gameHandler;
    private GraphicsHandler graphicsHandler;
    private JLabel statusText;

    public UserInterface(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.map = gameHandler.getMap();
    }

    @Override
    public void run() {
        frame = new JFrame("Dungeon Crawler");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        try {
            graphicsHandler = new GraphicsHandler(map);
        } catch (IOException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        container.add(graphicsHandler);
        statusText = new JLabel();
        statusText.setText("Health: " + gameHandler.getPlayer().getCurrentHealth());
        container.add(statusText, BorderLayout.SOUTH);
        KeyInputListener kl = new KeyInputListener(gameHandler);
        frame.addKeyListener(kl);
    }

    /**
     * This method requests all UI components to update their status to reflect
     * the current status of the game. This should be called once in the
     * beginning of each turn.
     */
    public void update() {
        graphicsHandler.repaint();
        statusText.setText("Health: " + gameHandler.getPlayer().getCurrentHealth());
    }

}
