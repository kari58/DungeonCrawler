package dungeoncrawler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dungeoncrawler.creature.Creature;
import dungeoncrawler.creature.NPC;
import dungeoncrawler.gameobject.Square;
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
public class CreatureTest {
    
    private NPC monster;
    private Square square;
    
    public CreatureTest() {
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
        monster = new NPC("monster", "m");
        monster.setSquare(square);
    }
    
    @Test
    public void symbolIsCorrectAfterCreation() {
        assertEquals(monster.getSymbol(), "m");
    }
    
    @Test
    public void settingSymbolWorks() {
        monster.setSymbol("x");
        assertEquals(monster.getSymbol(), "x");
    }
    
    @Test
    public void maxHealthIsCorrectAfterCreation() {
        assertEquals(monster.getMaxHealth(), 50);
    }
    
    @Test
    public void currentHealthIsCorrectAfterCreation() {
        assertEquals(monster.getMaxHealth(), 50);
    }
    
    @Test
    public void takingDamageWorks() {
        monster.takeDamage(25);
        assertEquals(monster.getCurrentHealth(), 25);
    }
    
    @Test
    public void cannotTakeNegativeDamage() {
        monster.takeDamage(-25);
        assertEquals(monster.getCurrentHealth(), 50);
    }
    
    @Test
    public void damageIsCorrectAfterCreation() {
        assertEquals(monster.getDamage(), 10);
    }
    
    @Test
    public void healthCannotBeLowerThanZero() {
        monster.takeDamage(100);
        assertEquals(monster.getCurrentHealth(), 0);
    }
    
    @Test
    public void settingSquareWorks() {
        assertEquals(monster.getSquare(), square);
    }
    
    @Test
    public void canSetHostileStatus() {
        monster.setHostileStatus(false);
        assertEquals(monster.isUnitHostile(), false);
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
