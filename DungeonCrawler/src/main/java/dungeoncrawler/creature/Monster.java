

package dungeoncrawler.creature;


public class Monster extends Creature {
    
    public Monster(boolean isHostile, String symbol, int maxHealth, int damage) {
        super(false, isHostile, symbol, maxHealth, damage);
    }

    @Override
    public void move() {
    }

}
