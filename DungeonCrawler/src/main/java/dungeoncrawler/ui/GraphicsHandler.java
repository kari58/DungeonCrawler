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
import java.util.HashMap;
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
    private HashMap<String, BufferedImage> spriteBuffer;

    public GraphicsHandler(Map map) throws IOException {
        super.setBackground(Color.WHITE);
        this.map = map;
        spriteBuffer = new HashMap<>();
        BufferedImage defaultImage = ImageIO.read(new File("images\\default.jpg"));
        spriteBuffer.put("default", defaultImage);
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
                BufferedImage bufferedImage = spriteBuffer.get("default");
                if (spriteBuffer.containsKey(path)) {
                    bufferedImage = spriteBuffer.get(path);
                } else {
                    try {
                        Image image = ImageIO.read(new File(path));
                        bufferedImage = graphicsConfiguration.createCompatibleImage(image.getWidth(null), image.getHeight(null));
                        Graphics tempGraphics = bufferedImage.getGraphics();
                        tempGraphics.drawImage(image, 0, 0, null);
                        tempGraphics.dispose();
                        spriteBuffer.put(path, bufferedImage);

                    } catch (IOException ex) {
                        Logger.getLogger(GraphicsHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                g.drawImage(bufferedImage, square.getX() * 30, square.getY() * 30, null);
            }
        }
    }

}
