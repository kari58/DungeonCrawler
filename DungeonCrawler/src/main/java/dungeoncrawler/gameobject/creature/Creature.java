package dungeoncrawler.gameobject.creature;

/**
 * This abstract class contains all information that is common to all creatures
 * within the world, including the player and monsters.
 *
 */
import dungeoncrawler.gameobject.GameObject;
import dungeoncrawler.map.Square;
import java.awt.Color;

public abstract class Creature extends GameObject {

    private int maxHealth;
    private int currentHealth;
    private int damage;

    public Creature(String name, Color color) {
        super(false, name, color);
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.damage = 5;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Causes damage to the creature. Reduces an amount of health equal to the
     * damage parameter. At 0 health, the creature will be considered dead.
     *
     * @param damage the amount of damage to be done to the creature
     */
    public void takeDamage(int damage) {
        if (damage > 0) {
            this.currentHealth -= damage;
            if (this.currentHealth < 0) {
                this.currentHealth = 0;
            }
        }

    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
