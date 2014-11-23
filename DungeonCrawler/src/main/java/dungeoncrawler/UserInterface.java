package dungeoncrawler;

import dungeoncrawler.map.Square;
import dungeoncrawler.map.Map;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable {

    private JFrame frame;
    private Map map;
    private GameHandler gameHandler;
    private List<Component> components;
    
    public UserInterface(Map map, GameHandler gameHandler) {
        this.map = map;
        this.gameHandler = gameHandler;
        this.components = new ArrayList<Component>();
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
        GraphicsHandler gh = new GraphicsHandler(map);
        container.add(gh);
        components.add(gh);
        KeyInputListener kl = new KeyInputListener(gameHandler);
        frame.addKeyListener(kl);
    }
    
    public void update() {
        for (Component c : components) {
            c.repaint();
        }
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
