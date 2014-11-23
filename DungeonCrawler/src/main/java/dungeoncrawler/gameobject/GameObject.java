package dungeoncrawler.gameobject;

import dungeoncrawler.map.Square;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This abstract class contains information and services common to all game
 * objects.
 */
public abstract class GameObject {

    boolean walkable;
    String name;
    Square square;
    Color color;

    public GameObject(boolean walkable, String name, Color color) {
        this.walkable = walkable;
        this.name = name;
        this.color = color;
    }


    public String getName() {
        return name;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }
    
    public void draw(Graphics g) {
        int x = this.getSquare().getX();
        int y = this.getSquare().getY();
        g.setColor(color);
        g.fillRect(x * 30, y * 30, 30, 30);
    }

}
