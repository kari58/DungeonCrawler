package dungeoncrawler.gameobject.creature;

import java.awt.Color;

/**
 * This class represents the player within the game. Only one player object
 * should exist within the game at a time.
 *
 * @author Keke
 */
public class Player extends Creature {

    private int level;
    private int xp;

    public Player() {
        super("adventurer");
        super.setPlayerStatus(true);
        super.setMaxHealth(200);
        super.setCurrentHealth(super.getMaxHealth());
        super.setDamage(40);
        level = 1;
        xp = 0;
        super.setSpritePath("images\\greenSquare.jpg");
    }
    
    /**
     * This method adds experience to the player. If the player's total
     * experience exceeds the required amount of experience to level up, the
     * player gains a level.
     *
     * @param amount the amount of experience the player should gain
     */
    public void gainXp(int amount) {
        xp += amount;
        while (xp > 1000 * level) {
            gainLevel();
        }
    }

    /**
     * This method determines what happens when the player gains a level. The
     * player's power gain is adjusted to approximately match the difficulty
     * increase per map level set in LevelData class.
     */
    private void gainLevel() {
        xp -= 1000 * level;
        level++;
        super.setDamage(super.getDamage() + (int) (1.5 * level));
        super.setMaxHealth(super.getMaxHealth() + (int) (level * 5));
        super.setCurrentHealth(super.getMaxHealth());
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getXp() {
        return xp;
    }

}
