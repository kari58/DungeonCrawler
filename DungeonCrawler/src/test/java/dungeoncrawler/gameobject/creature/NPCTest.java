package dungeoncrawler.gameobject.creature;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dungeoncrawler.gameobject.creature.Creature;
import dungeoncrawler.gameobject.creature.NPC;
import dungeoncrawler.map.Square;
import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Keke
 */
public class NPCTest {
    
    private NPC monster;
    private Square square;
    
    public NPCTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        square = new Square(1, 1);
        monster = new NPC("monster", true, Color.RED);
        monster.setSquare(square);
    }
    /*
    @Test
    public void symbolIsCorrectAfterCreation() {
        assertEquals(monster.getSymbol(), "m");
    }
    
    @Test
    public void settingSymbolWorks() {
        monster.setSymbol("x");
        assertEquals(monster.getSymbol(), "x");
    }*/
    
    @Test
    public void settingRandomMoveChanceWorks() {
        monster.setChanceToMoveRandomly(0.5);
        assertEquals(0.5, monster.getChanceToMoveRandomly(),0.01);
    }
    
    @Test
    public void takingDamageSetsNPCHostile() {
        monster.setHostileStatus(false);
        assertFalse(monster.isHostile());
        monster.takeDamage(5);
        assertTrue(monster.isHostile());
    }
    
    @Test
    public void takingDamageChangesNPCColorIfNPCWasNeutral() {
        monster.setHostileStatus(false);
        monster.setColor(Color.YELLOW);
        assertEquals(Color.YELLOW, monster.getColor());
        monster.takeDamage(5);
        assertEquals(Color.RED, monster.getColor());
    }
    
    @Test
    public void cannotTakeNegativeDamage() {
        int health = monster.getCurrentHealth();
        monster.takeDamage(-10);
        assertEquals(health, monster.getCurrentHealth());
    }
    
    @Test
    public void healthCannotGoBelowZero() {
        monster.takeDamage(5000);
        assertEquals(0, monster.getCurrentHealth());
    }
    
    @Test
    public void takingDamageReducesCorrectAmountOfHealth() {
        int health = monster.getCurrentHealth();
        monster.takeDamage(2);
        assertEquals(health - 2, monster.getCurrentHealth());
    }
    
    
    @Test
    public void playerStatusIsCorrect() {
        assertFalse(monster.isPlayer());
    }
    
    @Test
    public void maxHealthIsCorrectAfterCreation() {
        assertEquals(monster.getMaxHealth(), 100);
    }
    
    @Test
    public void currentHealthIsCorrectAfterCreation() {
        assertEquals(monster.getCurrentHealth(), monster.getMaxHealth());
    }
    
    @Test
    public void takingDamageWorks() {
        monster.takeDamage(25);
        assertEquals(monster.getCurrentHealth(), monster.getMaxHealth() - 25);
    }
    
    @Test
    public void healthCannotBeLowerThanZero() {
        monster.takeDamage(monster.getMaxHealth() + 10);
        assertEquals(monster.getCurrentHealth(), 0);
    }
    
    @Test
    public void settingSquareWorks() {
        assertEquals(monster.getSquare(), square);
    }
    
    @Test
    public void canSetHostileStatus() {
        monster.setHostileStatus(false);
        assertEquals(monster.isHostile(), false);
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
