

package dungeoncrawler.domain;


public class Monster extends Creature {
    
    public Monster(Square startingSquare, boolean isHostile, String symbol, int maxHealth, int damage) {
        super(startingSquare, isHostile, symbol, maxHealth, damage);
    }

    @Override
    public void move() {
    }

}
