package dungeoncrawler.gameobject.creature;


import dungeoncrawler.gameobject.GameObject;

/**
 * This abstract class contains all information that is common to all creatures
 * within the world, including the player and monsters. All monsters have the
 * following attributes: <br><br>
 *
 * - Max health<br>
 * - Current health<br>
 * - Damage<br>
 * <br>
 * Additionally, to avoid having to compare class type, a player-type Creature
 * will have isPlayer boolean set to true, others should be false. <br>
 *
 *
 */

public abstract class Creature extends GameObject {

    private int maxHealth;
    private int currentHealth;
    private int damage;
    private boolean isPlayer;

    public Creature(String name) {
        super(false, name);
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.damage = 5;
        this.isPlayer = false;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayerStatus(boolean playerStatus) {
        isPlayer = playerStatus;
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
     * damage parameter. Health cannot be reduced below 0. <br>
     * The logic for removing dead creatures from the game map is not
     * implemented in this class and should be handled by game logic handling
     * classes.
     *
     * @param damage the amount of damage to be done to the creature
     */
    public void takeDamage(int damage) {
        if (damage < 0) {
            return;
        }
        this.currentHealth -= damage;
        if (this.currentHealth < 0) {
            this.currentHealth = 0;
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
