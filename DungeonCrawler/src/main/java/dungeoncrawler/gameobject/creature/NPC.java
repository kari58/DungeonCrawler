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
    
    public NPC(String name, boolean isHostile) {
        super(name);
        this.isHostile = isHostile;
        this.aggroRadius = 2;
        this.chanceToMoveRandomly = 0.5;
    }


    /**
     * The chance that, when asked to move around randomly, the monster will
     * move instead of staying still.
     *
     * @return the chance for the NPC to move around randomly
     */
    public double getChanceToMoveRandomly() {
        return chanceToMoveRandomly;
    }

    public void setChanceToMoveRandomly(double chanceToMoveRandomly) {
        this.chanceToMoveRandomly = chanceToMoveRandomly;
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

    /**
     * This method causes the NPC to take damage. Damage calculation is done by
     * invoking the takeDamage() method of the inherited Creature-class. In
     * addition, if the NPC was not hostile before, it is set to hostile and its
     * appearance is changed to match that it is hostile.
     *
     * @param damage amount of damage to be taken
     */
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isHostile) {
            this.isHostile = true;
            super.setSpritePath("images\\redSquare.jpg");
        }
    }

}
