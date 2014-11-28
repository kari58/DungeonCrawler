package dungeoncrawler.ui;

import dungeoncrawler.gameobject.GameObject;
import dungeoncrawler.map.Map;
import dungeoncrawler.map.Square;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        for (Square[] row : map.getMap()) {
            for (Square square : row) {
                String path = "";
                if (square.getCreatureOnSquare() != null) {
                    path = square.getCreatureOnSquare().getSpritePath();
                } else if (square.getObjectOnSquare() != null) {
                    path = square.getObjectOnSquare().getSpritePath();
                } else {
                    path = square.getSpritePath();
                }

                try {
                    Image image = ImageIO.read(new File(path));
                    BufferedImage bufferedImage = graphicsConfiguration.createCompatibleImage(image.getWidth(null), image.getHeight(null));
                    Graphics tempGraphics = bufferedImage.getGraphics();
                    tempGraphics.drawImage(image, 0, 0, null);
                    tempGraphics.dispose();
                    g.drawImage(bufferedImage, square.getX() * 30, square.getY() * 30, null);

                } catch (IOException ex) {
                    Logger.getLogger(GraphicsHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
