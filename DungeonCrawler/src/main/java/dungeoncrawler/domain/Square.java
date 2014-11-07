

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
        // Check to make sure that there isn't already an object occupying the square, that the entity entering the square isn't null
        // and that the entity entering the square isn't another square
        if (this.entityOnSquare != null && entity != null && entity.getClass() != this.getClass()) {
            this.entityOnSquare = entity;
        }
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
