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
    private double chanceToMoveRandomly;

    public double getChanceToMoveRandomly() {
        return chanceToMoveRandomly;
    }

    public void setChanceToMoveRandomly(double chanceToMoveRandomly) {
        this.chanceToMoveRandomly = chanceToMoveRandomly;
    }

    public NPC(String name, boolean isHostile, Color color) {
        super(name, color);
        this.isHostile = isHostile;
        this.aggroRadius = 2;
        this.chanceToMoveRandomly = 0.5;
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

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isHostile) {
            this.isHostile = true;
            super.setColor(Color.RED);
        }
    }

}
