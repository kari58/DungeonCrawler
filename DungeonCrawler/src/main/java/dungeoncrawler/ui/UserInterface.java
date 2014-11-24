package dungeoncrawler.ui;

import dungeoncrawler.logic.GameHandler;
import dungeoncrawler.map.Square;
import dungeoncrawler.map.Map;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

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
        graphicsHandler = new GraphicsHandler(map);
        container.add(graphicsHandler);
        statusText = new JLabel();
        statusText.setText("Health: " + gameHandler.getPlayer().getCurrentHealth());
        container.add(statusText, BorderLayout.SOUTH);
        KeyInputListener kl = new KeyInputListener(gameHandler);
        frame.addKeyListener(kl);
    }

    public void update() {
        graphicsHandler.repaint();
        statusText.setText("Health: " + gameHandler.getPlayer().getCurrentHealth());
    }

}
