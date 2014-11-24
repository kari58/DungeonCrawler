/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncrawler.gameobject.creature;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



public class PlayerTest {
    
    private Player player;
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        player = new Player();
    }
    
    @Test
    public void startingStatsAreCorrect() {
        assertEquals(200, player.getMaxHealth());
        assertEquals(player.getMaxHealth(), player.getCurrentHealth());
        assertEquals(40, player.getDamage());
        assertEquals(1, player.getLevel());
        assertEquals(0, player.getXp());
    }
    
    @Test
    public void canGainXP() {
        int xp = player.getXp();
        player.gainXp(50);
        assertEquals(xp + 50, player.getXp());
        player.gainXp(25);
        assertEquals(xp + 75, player.getXp());        
    }
    
    @Test
    public void canGainLevels() {
        int level = player.getLevel();
        player.gainXp(10000);
        assertTrue(level < player.getLevel());
    }
    
    @Test
    public void gainingLevelsResetsXp() {
        player.gainXp(10000);
        assertTrue(10000 > player.getXp());
    }
    
    @Test
    public void gainingLevelsIncreasesStats() {
        int maxHealth = player.getMaxHealth();
        int curHealth = player.getCurrentHealth();
        int damage = player.getDamage();
        player.gainXp(10000);
        assertTrue(maxHealth < player.getMaxHealth());
        assertTrue(curHealth < player.getCurrentHealth());
        assertTrue(damage < player.getDamage());
    }
    
    @Test
    public void playerStatusIsCorrect() {
        assertTrue(player.isPlayer());
    }
    
    
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
