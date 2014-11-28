package dungeoncrawler.gameobject;

/**
 * This class encapsulates a stairs object. Stairs are used to move to the next
 * level. Moving backwards to previous levels is not possible.
 *
 */
public class Stairs extends GameObject {

    public Stairs() {
        super(true, "stairs");
        super.setSpritePath("images\\stairs.jpg");
    }

}
