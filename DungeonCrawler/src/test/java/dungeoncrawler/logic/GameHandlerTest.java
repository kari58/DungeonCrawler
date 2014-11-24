/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncrawler.logic;

import dungeoncrawler.gameobject.creature.Creature;
import dungeoncrawler.gameobject.creature.NPC;
import dungeoncrawler.map.Square;
import java.awt.Color;
import java.awt.event.KeyEvent;
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
public class GameHandlerTest {
    
    public GameHandlerTest() {
    }
    
    private GameHandler gameHandler;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        gameHandler = new GameHandler();
    }
    
    @Test    
    public void gameStatusUpdatesWhenGameEnds() {
        assertFalse(gameHandler.gameHasEnded());
        gameHandler.handleGameEnd();
        assertTrue(gameHandler.gameHasEnded());
    }
    
    @Test
    public void canGetPlayer() {
        assertNotNull(gameHandler.getPlayer());
    }
    
    @Test
    public void canGetMap() {
        assertNotNull(gameHandler.getMap());
    }
    
    @Test
    public void canGetActionHandler() {
        assertNotNull(gameHandler.getActionHandler());
    }
    
    @Test
    public void cannotMoveAfterGameEnds() {
        gameHandler.handleGameEnd();
        int playerX = gameHandler.getPlayer().getSquare().getX();
        int playerY = gameHandler.getPlayer().getSquare().getY();
        performRandomMoves();
        assertEquals(playerX, gameHandler.getPlayer().getSquare().getX());
        assertEquals(playerY, gameHandler.getPlayer().getSquare().getY());
        
    }
    
    @Test
    public void noActionsTakenWhenPlayerTriesToMoveToWall() {
        gameHandler.getPlayer().getSquare().setCreatureOnSquare(null);
        gameHandler.getMap().getMap()[1][1].setCreatureOnSquare(gameHandler.getPlayer());
        gameHandler.getPlayer().setSquare(gameHandler.getMap().getMap()[1][1]);
        int playerX = gameHandler.getPlayer().getSquare().getX();
        int playerY = gameHandler.getPlayer().getSquare().getY();
        gameHandler.handleMovement(KeyEvent.VK_UP);
        gameHandler.handleMovement(KeyEvent.VK_UP);
        gameHandler.handleMovement(KeyEvent.VK_UP);
        gameHandler.handleMovement(KeyEvent.VK_UP);
        assertEquals(playerX, gameHandler.getPlayer().getSquare().getX());
        assertEquals(playerY, gameHandler.getPlayer().getSquare().getY());
        
    }
    
    private void performRandomMoves() {
        int[] directions = new int[4];
        directions[0] = KeyEvent.VK_LEFT;
        directions[1] = KeyEvent.VK_RIGHT;
        directions[2] = KeyEvent.VK_UP;
        directions[3] = KeyEvent.VK_DOWN;
        Random rng = new Random();        
        for (int i = 0; i < 15; i++) {
            int direction = directions[rng.nextInt(directions.length)];
            gameHandler.handleMovement(direction);
        }
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
