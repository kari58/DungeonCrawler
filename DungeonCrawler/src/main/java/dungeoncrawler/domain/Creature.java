package dungeoncrawler.domain;

public abstract class Creature extends GameObject {

    private Square square;
    private boolean isHostile;
    private int maxHealth;
    private int currentHealth;
    private int damage;

    public Creature(boolean walkable, boolean hostileStatus, String symbol, int maxHealth, int damage) {
        super(walkable, symbol);
        this.isHostile = hostileStatus;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.damage = damage;
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
