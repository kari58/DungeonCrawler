package dungeoncrawler.map;

/**
 * This class encapsulates a square. The game map is composed of objects of this
 * class. Each square may contain a game object such as a wall, or a creature
 * such as a monster or the player
 */
import dungeoncrawler.gameobject.creature.Creature;
import dungeoncrawler.gameobject.GameObject;

public class Square {

    private final int x;
    private final int y;
    private GameObject objectOnSquare; // includes non-creature objects like walls, stairs etc.
    private Creature creatureOnSquare;
    private String symbol;

    public Square(int x, int y) {
        this.symbol = ".";
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

    public String getSymbol() {
        return symbol;
    }

}
