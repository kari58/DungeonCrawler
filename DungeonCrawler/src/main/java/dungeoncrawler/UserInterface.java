package dungeoncrawler;

import dungeoncrawler.map.Square;
import dungeoncrawler.map.Map;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable {

    private JFrame frame;
    private Map map;
    
    public UserInterface(Map map) {
        this.map = map;
    }

    @Override
    public void run() {
        frame = new JFrame("Dungeon Crawler");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane(), map);

        frame.pack();
        frame.setVisible(true);

    }

    private void createComponents(Container container, Map map) {
        GraphicsHandler gh = new GraphicsHandler(map);
        container.add(gh);

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
