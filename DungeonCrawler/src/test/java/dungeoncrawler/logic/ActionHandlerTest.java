/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncrawler.logic;

import dungeoncrawler.gameobject.Stairs;
import dungeoncrawler.gameobject.creature.Player;
import dungeoncrawler.map.Map;
import dungeoncrawler.map.Square;
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
public class ActionHandlerTest {

    private GameHandler gameHandler;
    private ActionHandler actionHandler;

    public ActionHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        gameHandler = new GameHandler();
        actionHandler = gameHandler.getActionHandler();

    }

    @Test
    public void pressingEnterAlwaysReturnsFalse() {
        assertFalse(actionHandler.handlePlayerMove(KeyEvent.VK_ENTER));
        gameHandler.getPlayer().getSquare().setObjectOnSquare(new Stairs());
        assertFalse(actionHandler.handlePlayerMove(KeyEvent.VK_ENTER));
    }

    @Test
    public void pressingEnterOnStairsMovesPlayerDownALevel() {
        gameHandler.getPlayer().getSquare().setObjectOnSquare(new Stairs());
        Square[][] map = gameHandler.getMap().getMap();
        actionHandler.handlePlayerMove(KeyEvent.VK_ENTER);
        Square[][] map2 = gameHandler.getMap().getMap();
        assertFalse(map.length == map2.length);
    }

    @Test
    public void movingToEachDirectionWorks() {
        Square[][] map = gameHandler.getMap().getMap();
        Player player = gameHandler.getPlayer();
        map = placePlayerAtX2Y2AndRemoveObstaclesFromAroundHim(map);
        map = removeMonstersFromMap(map);
        int origX = player.getSquare().getX();
        int origY = player.getSquare().getY();
        actionHandler.handlePlayerMove(KeyEvent.VK_LEFT);
        assertEquals(player, map[origX - 1][origY].getCreatureOnSquare());
        actionHandler.handlePlayerMove(KeyEvent.VK_RIGHT);
        actionHandler.handlePlayerMove(KeyEvent.VK_RIGHT);
        assertEquals(player, map[origX + 1][origY].getCreatureOnSquare());
        actionHandler.handlePlayerMove(KeyEvent.VK_LEFT);
        actionHandler.handlePlayerMove(KeyEvent.VK_UP);
        assertEquals(player, map[origX][origY - 1].getCreatureOnSquare());
        actionHandler.handlePlayerMove(KeyEvent.VK_DOWN);
        actionHandler.handlePlayerMove(KeyEvent.VK_DOWN);
        assertEquals(player, map[origX][origY + 1].getCreatureOnSquare());
    }

    @Test
    public void NPCsMoveWhenPlayerMoves() {
        Map map = gameHandler.getMap();
        Player player = gameHandler.getPlayer();
        boolean anNPCHasMoved = false;
        for (int i = 0; i < 10; i++) {
            map.updateMapForNewLevel(player);
        }
        boolean[][] unoccupiedCoordinates = new boolean[map.getWidth()][map.getHeight()];
        for (Square[] row : map.getMap()) {
            for (Square square : row) {
                if (square.getCreatureOnSquare() != null) {
                    if (square.getCreatureOnSquare().isPlayer()) {
                        unoccupiedCoordinates[square.getX()][square.getY()] = true;
                    } else {
                        unoccupiedCoordinates[square.getX()][square.getY()] = false;
                    }
                } else {
                    unoccupiedCoordinates[square.getX()][square.getY()] = true;
                }
            }
        }
        reduceAllCreaturesDamageToZero(map.getMap());
        int[] directions = {KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN};
        Random rng = new Random();
        for (int i = 0; i < 15; i++) {
            int direction = directions[rng.nextInt(4)];
            gameHandler.handleMovement(direction);
            for (Square[] row : map.getMap()) {
                for (Square square : row) {
                    if (square.getCreatureOnSquare() != null) {
                        if (!square.getCreatureOnSquare().isPlayer()) {
                            if (unoccupiedCoordinates[square.getX()][square.getY()]) {
                                anNPCHasMoved = true;
                            }
                        }
                    }
                }
            }

        }
        assertTrue(anNPCHasMoved);

    }

    private void reduceAllCreaturesDamageToZero(Square[][] map) {
        for (Square[] row : map) {
            for (Square square : row) {
                if (square.getCreatureOnSquare() != null) {
                    square.getCreatureOnSquare().setDamage(0);
                }
            }
        }
    }

    private Square[][] removeMonstersFromMap(Square[][] map) {
        for (Square[] row : map) {
            for (Square square : row) {
                if (square.getCreatureOnSquare() != null) {
                    if (!square.getCreatureOnSquare().isPlayer()) {
                        square.getCreatureOnSquare().setSquare(null);
                        map[square.getX()][square.getY()].setCreatureOnSquare(null);
                    }
                }
            }
        }
        return map;
    }

    private Square[][] placePlayerAtX2Y2AndRemoveObstaclesFromAroundHim(Square[][] map) {
        Player player = gameHandler.getPlayer();
        player.getSquare().setCreatureOnSquare(null);
        player.setSquare(map[2][2]);
        map[2][2].setObjectOnSquare(null);
        map[2][2].setCreatureOnSquare(player);
        map[1][2].setObjectOnSquare(null);
        map[1][2].setCreatureOnSquare(null);
        map[3][2].setObjectOnSquare(null);
        map[3][2].setCreatureOnSquare(null);
        map[2][3].setObjectOnSquare(null);
        map[2][3].setCreatureOnSquare(null);
        map[2][1].setObjectOnSquare(null);
        map[2][1].setCreatureOnSquare(null);
        return map;

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
