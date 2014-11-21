package dungeoncrawler.map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dungeoncrawler.gameobject.creature.Creature;
import dungeoncrawler.gameobject.GameObject;
import dungeoncrawler.gameobject.GameObject;
import dungeoncrawler.gameobject.creature.NPC;
import dungeoncrawler.map.Square;
import dungeoncrawler.gameobject.Wall;
import dungeoncrawler.gameobject.Wall;
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
    public void canSetCreatureOnSquare() {
        Creature m = new NPC("creature", "m");
        square.setCreatureOnSquare(m);
        assertNotNull(square.getCreatureOnSquare());
        Creature x = square.getCreatureOnSquare();
        assertEquals(x, m);
    }
    
    @Test
    public void removeCreatureWorks() {
        Creature m = new NPC("creature", "m");
        square.setCreatureOnSquare(m);
        assertNotNull(square.getCreatureOnSquare());
        square.removeCreatureOnSquare();
        assertNull(square.getCreatureOnSquare());
    }
    
    @Test
    public void canSetObjectOnSquare() {
        GameObject m = new Wall();
        square.setObjectOnSquare(m);
        assertNotNull(square.getObjectOnSquare());
        GameObject x = square.getObjectOnSquare();
        assertEquals(x, m);
    }
    
    @Test
    public void removeObjectWorks() {
        GameObject m = new Wall();
        square.setObjectOnSquare(m);
        assertNotNull(square.getObjectOnSquare());
        square.removeObjectOnSquare();
        assertNull(square.getObjectOnSquare());
    }
    
    
    
    @After
    public void tearDown() {
    }

}
