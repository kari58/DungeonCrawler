/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncrawler.map;

import dungeoncrawler.creature.Player;
import dungeoncrawler.gameobject.Square;
import dungeoncrawler.map.Map;
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
public class MapTest {
    
    private Map map;
    
    public MapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        map = new Map(new Player());
    }
    
    @Test
    public void canGetMap() {
        assertNotNull(map.getMap());
    }
    
    @Test
    public void updatingMapGeneratesNewMapForNewLevel() {
        Square[][] mapOne = map.getMap();
        map.updateMapForNewLevel(new Player());
        assertTrue(mapOne.length < map.getMap().length);
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
