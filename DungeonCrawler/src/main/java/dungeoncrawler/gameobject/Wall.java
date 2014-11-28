package dungeoncrawler.gameobject;

/**
 * This class encapsulates a wall object. Walls are impassable. Each wall object
 * occupies one square and larger walls are constructed from individual wall
 * objects.
 *
 */
public class Wall extends GameObject {

    public Wall() {
        super(false, "wall");
        super.setSpritePath("images\\graySquare.jpg");
    }

}
