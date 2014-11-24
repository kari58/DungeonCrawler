package dungeoncrawler.map;

/**
 * This class encapsulates a square. The game map is composed of objects of this
 * class. Each square may contain a game object such as a wall, or a creature
 * such as a monster or the player
 */
import dungeoncrawler.gameobject.creature.Creature;
import dungeoncrawler.gameobject.GameObject;
import java.awt.Color;
import java.awt.Graphics;

public class Square {

    private final int x;
    private final int y;
    private GameObject objectOnSquare; 
    private Creature creatureOnSquare;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public Creature getCreatureOnSquare() {
        return creatureOnSquare;
    }

    public void setCreatureOnSquare(Creature creatureOnSquare) {
        this.creatureOnSquare = creatureOnSquare;
    }

    public void removeCreatureOnSquare() {
        this.creatureOnSquare = null;
    }

    public GameObject getObjectOnSquare() {
        return objectOnSquare;
    }

    public void setObjectOnSquare(GameObject object) {
        this.objectOnSquare = object;
    }

    public void removeObjectOnSquare() {
        this.objectOnSquare = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(x * 30, y * 30, 30, 30);
    }
}
