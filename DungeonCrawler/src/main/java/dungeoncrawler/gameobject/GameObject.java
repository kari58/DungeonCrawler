package dungeoncrawler.gameobject;

import dungeoncrawler.map.Square;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * This abstract class contains information and services common to all game
 * objects. Game objects all occupy a square, may or may not be walked through,
 * have a name and are able to draw themselves.
 */
public abstract class GameObject {

    private boolean walkable;
    private String name;
    private Square square;
    private String spritePath;

    public GameObject(boolean walkable, String name) {
        this.walkable = walkable;
        this.name = name;
    }

    public String getSpritePath() {
        return spritePath;
    }
    
    public void setSpritePath(String path) {
        this.spritePath = path;
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
    
    
    /**
     * This method draws a 30x30 pixel representation of the game object, which
     * by default is a mono-color square. The color of the square is determined
     * by the game object class.
     * 
     * @param g Graphics object required by Swing for drawing
     *//*
    public void draw(Graphics g) {
        int x = this.getSquare().getX();
        int y = this.getSquare().getY();
        g.setColor(color);
        g.fillRect(x * 30, y * 30, 30, 30);
        
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return color;
    }*/
    

}
