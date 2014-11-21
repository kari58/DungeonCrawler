package dungeoncrawler.map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dungeoncrawler.gameobject.creature.Player;
import dungeoncrawler.map.MapHandler;
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
public class MapHandlerTest {

    private MapHandler mh;
    private Player player;

    public MapHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mh = new MapHandler();
        player = new Player();
    }

    @Test
    public void nothingOverlapsWithUnwalkableObjects() {
        Square[][] map;
        for (int i = 0; i < 100; i++) {
            map = mh.createNewMap(player);
            for (Square[] squares : map) {
                for (Square square : squares) {
                    if (square.getObjectOnSquare() != null) {
                        if (!square.getObjectOnSquare().isWalkable()) {
                            assertNull(square.getCreatureOnSquare());
                        }
                    }
                }
            }
        }
    }

    @Test
    public void mapSizeIncreasesWithEachLevel() {
        Square[][] map = mh.createNewMap(player);
        int previousSizeX = map.length;
        int previousSizeY = map.length;
        for (int i = 0; i < 5; i++) {
            map = mh.createNewMap(player);
            int currentSizeX = map.length;
            int currentSizeY = map[0].length;
            assertTrue(currentSizeX > previousSizeX && currentSizeY > previousSizeY);
        }
    }

    @Test
    public void numberOfCreaturesAlwaysIncreasesByTwoEachLevel() {
        for (int i = 0; i < 15; i++) {
            mh = new MapHandler();
            Square[][] map = mh.createNewMap(player);
            int previousNumberOfCreatures = countCreaturesOnMap(map);
            for (int y = 0; y < 5; y++) {
                map = mh.createNewMap(player);
                int currentNumberOfCreatures = countCreaturesOnMap(map);
                assertEquals(currentNumberOfCreatures, previousNumberOfCreatures + 2);
                previousNumberOfCreatures = currentNumberOfCreatures;
            }
        }

    }
    
    @Test
    public void mapEdgesAreSurroundedByWalls() {
        Square[][] map = mh.createNewMap(player);
        for (Square[] squares : map) {
            for (Square square : squares) {
                if (square.getX() == 0 || square.getY() == 0 || square.getX() == map.length - 1 || square.getY() == map[0].length) {
                    assertNotNull(square.getObjectOnSquare());
                    assertEquals("wall", square.getObjectOnSquare().getName());
                }
            }
        }
    }

    private int countCreaturesOnMap(Square[][] map) {
        int creatures = 0;
        for (Square[] squares : map) {
            for (Square square : squares) {
                if (square.getCreatureOnSquare() != null) {
                    creatures++;
                }
            }
        }
        return creatures;
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
