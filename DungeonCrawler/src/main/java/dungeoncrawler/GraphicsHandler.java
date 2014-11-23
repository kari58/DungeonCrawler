

package dungeoncrawler;

import dungeoncrawler.gameobject.GameObject;
import dungeoncrawler.map.Map;
import dungeoncrawler.map.Square;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class GraphicsHandler extends JPanel {
    
    private Map map;
    
    public GraphicsHandler(Map map) {
        super.setBackground(Color.WHITE);
        this.map = map;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        for (Square[] row : map.getMap()) {
            for (Square square : row) {
                if (square.getCreatureOnSquare() != null) {
                    square.getCreatureOnSquare().draw(g);
                }
                else if (square.getObjectOnSquare() != null) {
                    square.getObjectOnSquare().draw(g);
                }
                else {
                    square.draw(g);
                }
            }
        }
    }
    

}
