package dungeoncrawler.creature;






public class Player extends Creature {
    
    private int level;
    private int xp;

    public Player() {
        super("adventurer", "@");
        level = 1;
        xp = 0;
    }

    @Override
    public void move() {
    }
    
    public void gainXp(int amount) {
        xp += amount;
        if (xp > 1000 * level) {
            gainLevel();
        }
    }
    
    private void gainLevel() {
        xp -= 1000 * level;
        level++;
        super.setDamage(super.getDamage() + (int) (1.4 * level));
        super.setMaxHealth((int) (super.getMaxHealth() * (1 + level / 9)));
        super.setCurrentHealth(super.getMaxHealth());
    }

}
