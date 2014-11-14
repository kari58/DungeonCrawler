package dungeoncrawler.creature;

import dungeoncrawler.gameobject.GameObject;
import dungeoncrawler.gameobject.Square;

public abstract class Creature extends GameObject {

    private Square square;
    private boolean isHostile;
    private int maxHealth;
    private int currentHealth;
    private int damage;

    public Creature(String name, String symbol) {
        super(false, name, symbol);
        this.isHostile = true;
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.damage = 5;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
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

    abstract public void move();

    public void setHostileStatus(boolean hostileStatus) {
        this.isHostile = hostileStatus;
    }

    public boolean isUnitHostile() {
        return isHostile;
    }

}
