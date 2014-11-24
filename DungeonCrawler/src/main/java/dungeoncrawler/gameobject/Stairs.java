package dungeoncrawler.gameobject;

import java.awt.Color;

/**
 * This class encapsulates a stairs object. Stairs are used to the next level.
 * Moving backwards to previous levels is not possible.
 *
 */
public class Stairs extends GameObject {

    public Stairs() {
        super(true, "stairs", Color.ORANGE);
    }

}
