package dungeoncrawler;

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
    
    public UserInterface(Map map, GameHandler gameHandler) {
        this.map = map;
        this.gameHandler = gameHandler;
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
/*
    public void drawMap(Map mapObject) {
        Square[][] map = mapObject.getMap();
        for (Square[] squares : map) {
            for (Square square : squares) {
                if (square.getCreatureOnSquare() != null) {
                    System.out.print(square.getCreatureOnSquare().getSymbol());
                } else if (square.getObjectOnSquare() != null) {
                    System.out.print(square.getObjectOnSquare().getSymbol());
                } else {
                    System.out.print(square.getSymbol());
                }
            }
            System.out.println("");
        }
    }*/

}
