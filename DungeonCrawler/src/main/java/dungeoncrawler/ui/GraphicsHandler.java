package dungeoncrawler.ui;

import dungeoncrawler.gameobject.GameObject;
import dungeoncrawler.map.Map;
import dungeoncrawler.map.Square;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * This class manages the graphical representation of the game map and the game
 * objects within the map.
 */
public class GraphicsHandler extends JPanel {

    private Map map;

    public GraphicsHandler(Map map) {
        super.setBackground(Color.WHITE);
        this.map = map;
    }

    /**
     * This method will draw objects on the game map. If a creature is found on
     * a square, it will be drawn. If no creature is found, but a game object is
     * found, it will be drawn. If neither a creature or a game object is found,
     * the square will be drawn.
     *
     * @param g the Graphics object required by Swing to draw things
     */
    @Override
    protected void paintComponent(Graphics g) {
        for (Square[] row : map.getMap()) {
            for (Square square : row) {
                if (square.getCreatureOnSquare() != null) {
                    square.getCreatureOnSquare().draw(g);
                } else if (square.getObjectOnSquare() != null) {
                    square.getObjectOnSquare().draw(g);
                } else {
                    square.draw(g);
                }
            }
        }
    }

}
