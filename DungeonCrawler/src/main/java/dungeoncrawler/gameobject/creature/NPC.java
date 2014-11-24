package dungeoncrawler.gameobject.creature;

import java.awt.Color;

/**
 * This class represents an NPC within the game world. Both non-hostile NPCs and
 * hostile monsters can be represented as objects of this class.
 *
 * @author Keke
 */
public class NPC extends Creature {

    private boolean isHostile;
    private int aggroRadius;

    public NPC(String name, boolean isHostile, Color color) {
        super(name, color);
        this.isHostile = isHostile;
        this.aggroRadius = 2;
    }

    public void setAggroRadius(int radius) {
        aggroRadius = radius;
    }

    public int getAggroRadius() {
        return aggroRadius;
    }

    public void setHostileStatus(boolean hostileStatus) {
        this.isHostile = hostileStatus;
    }

    public boolean isHostile() {
        return isHostile;
    }

}
