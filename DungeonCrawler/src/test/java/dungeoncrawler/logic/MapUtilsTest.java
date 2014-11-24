/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncrawler.logic;

import dungeoncrawler.gameobject.Stairs;
import dungeoncrawler.gameobject.Wall;
import dungeoncrawler.gameobject.creature.Creature;
import dungeoncrawler.gameobject.creature.NPC;
import dungeoncrawler.gameobject.creature.Player;
import dungeoncrawler.map.Map;
import dungeoncrawler.map.Square;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class MapUtilsTest {
    
    public MapUtilsTest() {
    }
    
    private MapUtils mapUtils;
    private Map map;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        map = new Map(new Player());
        mapUtils = new MapUtils(map);
    }
    
    @Test
    public void squaresAreMovableWhenTheyContainNoCreaturesOrBlockingObjects() {
        Square square = new Square(1,1);
        assertTrue(mapUtils.squareIsMovable(square));
        Stairs stairs = new Stairs(); 
        square.setObjectOnSquare(stairs);
        assertTrue(mapUtils.squareIsMovable(square));        
    }
    
    @Test
    public void squaresAreNotMovableWhenTheyContainBlockingObjects() {
        Square square = new Square(1,1);
        Creature monster = new NPC("monster", true, Color.RED);
        square.setCreatureOnSquare(monster);
        assertFalse(mapUtils.squareIsMovable(square));
        square.removeCreatureOnSquare();
        assertTrue(mapUtils.squareIsMovable(square));
        Wall wall = new Wall();
        square.setObjectOnSquare(wall);
        assertFalse(mapUtils.squareIsMovable(square));
    }
    
    @Test
    public void distanceBetweenTwoSquaresIsCalculatedCorrectly() {
        Random rng = new Random();
        Square square1 = new Square(rng.nextInt(50), rng.nextInt(50));
        Square square2 = new Square(rng.nextInt(50), rng.nextInt(50));
        assertEquals(mapUtils.getDistanceToTarget(square1, square2), Math.abs(square1.getX() - square2.getX()) + Math.abs(square1.getY() - square2.getY()));
    }
    
    @Test
    public void amountOfMovableAdjacentSquaresTakesIntoAccountWalls() {
        Square square = map.getMap()[0][0];
        List squares = mapUtils.getMovableAdjacentSquares(square);
        assertEquals(0, squares.size());
        Square square2 = map.getMap()[1][1];
        squares = mapUtils.getMovableAdjacentSquares(square2);
        assertEquals(2, squares.size());
        Square square3 = map.getMap()[2][2];
        squares = mapUtils.getMovableAdjacentSquares(square3);
        assertEquals(4, squares.size());
    }
    
    @Test
    public void amountOfMovableAdjacentSquaresChecksForMapBorders() {
        Square square = map.getMap()[1][0];
        List squares = mapUtils.getMovableAdjacentSquares(square);
        assertEquals(map.getMap()[1][1], squares.get(0));
        square = map.getMap()[0][1];
        squares = mapUtils.getMovableAdjacentSquares(square);
        assertEquals(map.getMap()[1][1], squares.get(0));
        square = map.getMap()[map.getWidth() - 1][1];
        squares = mapUtils.getMovableAdjacentSquares(square);
        assertEquals(map.getMap()[map.getWidth() - 2][1], squares.get(0));
        square = map.getMap()[1][map.getHeight() - 1];
        squares = mapUtils.getMovableAdjacentSquares(square);
        assertEquals(map.getMap()[1][map.getHeight() - 2], squares.get(0));
    }
    
    @Test
    public void randomAdjacentSquareIsActuallyRandom() {
        Square square = map.getMap()[4][4];
        boolean squareVaries = false;
        Square comparison = mapUtils.pickRandomAdjacentMovableSquare(square);
        for (int i = 0; i < 20; i++) {
            Square square2 = mapUtils.pickRandomAdjacentMovableSquare(square);
            if (square2.getX() != comparison.getX() || square2.getY() != comparison.getY()) {
                squareVaries = true;
            }
        }
        assertTrue(squareVaries);
    }
    
    @Test
    public void nearestSquareToTargetIsCorrect() {
        Square target = map.getMap()[4][4];
        Square from1 = map.getMap()[2][4];
        Square from2 = map.getMap()[2][3];
        ArrayList<Square> squares = new ArrayList<Square>();
        squares.add(from1);
        squares.add(from2);
        assertEquals(from1, mapUtils.findSquareNearestToTarget(squares, target));
        squares.clear();
        from1 = map.getMap()[0][1];
        from2 = map.getMap()[5][6];
        Square from3 = map.getMap()[8][9];
        Square from4 = map.getMap()[9][9];
        squares.add(from1);
        squares.add(from2);
        squares.add(from3);
        squares.add(from4);
        assertEquals(from2, mapUtils.findSquareNearestToTarget(squares, target));
    }
    
    @Test
    public void pickRandomAdjacentSquareReturnsNullIfNoSquaresAreFound() {
        Square square = map.getMap()[0][0];
        assertNull(mapUtils.pickRandomAdjacentMovableSquare(square));
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
