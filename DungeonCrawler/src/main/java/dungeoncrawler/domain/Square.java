package dungeoncrawler.domain;

public class Square extends GameObject {

    private final int x;
    private final int y;
    private GameObject objectOnSquare; // includes non-creature objects like walls, stairs etc.
    private Creature creatureOnSquare;

    public Square(int x, int y) {
        super(true, ".");
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

    

}
