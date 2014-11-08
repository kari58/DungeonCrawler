/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dungeoncrawler.domain.Creature;
import dungeoncrawler.domain.GameObject;
import dungeoncrawler.domain.Monster;
import dungeoncrawler.domain.Square;
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
public class SquareTest {
    
    private Square square;
    
    public SquareTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.square = new Square(2,2);
    }
    
    @Test
    public void coordinatesAreCorrectAfterCreation() {
        assertEquals(square.getX(), 2);
        assertEquals(square.getY(), 2);
    }
    
    @Test
    public void canSetEntityOnSquare() {
        Creature m = new Monster(true, "m", 100, 10);
        assertNotNull(m);
        square.setCreatureOnSquare(m);
        assertNotNull(square.getCreatureOnSquare());
        Creature x = square.getCreatureOnSquare();
        assertEquals(x, m);
    }
    
    @Test
    public void removeEntityWorks() {
        Creature m = new Monster(true, "m", 100, 10);
        square.setCreatureOnSquare(m);
        assertNotNull(square.getCreatureOnSquare());
        square.removeCreatureOnSquare();
        assertNull(square.getCreatureOnSquare());
    }
    
    
    
    @After
    public void tearDown() {
    }

}
