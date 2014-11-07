package dungeoncrawler.domain;

public class Square extends GameObject {

    private final int x;
    private final int y;
    private GameObject entityOnSquare;

    public Square(int x, int y) {
        super(".");
        this.x = x;
        this.y = y;

    }

    public GameObject getEntity() {
        return entityOnSquare;
    }

    public void setEntity(GameObject entity) {
        this.entityOnSquare = entity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void removeEntity() {
        entityOnSquare = null;
    }

}
