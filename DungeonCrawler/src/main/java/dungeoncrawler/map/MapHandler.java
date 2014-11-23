package dungeoncrawler.map;

import dungeoncrawler.gameobject.creature.Creature;
import dungeoncrawler.gameobject.creature.NPC;
import dungeoncrawler.gameobject.creature.Player;
import dungeoncrawler.gameobject.Wall;
import dungeoncrawler.gameobject.Stairs;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class contains the logic necessary for creating maps. The map itself is
 * not contained within the class, but the class can generate maps.
 *
 */
public class MapHandler {

    private final LevelData levelData;
    private final MonsterGenerator monsterGenerator;

    public MapHandler() {
        this.levelData = new LevelData();
        this.monsterGenerator = new MonsterGenerator();
    }

    /**
     * This method generates a new map consisting of Square objects. Each time
     * this method is called, the level counter is incremented by one. Each
     * subsequent call of this method increases the level difficulty. The method
     * should be called only once per map level.
     *
     * @param player reference to the player object so that the player can be
     * added to the map
     * @return a 2-dimensional array of Square objects, representing the map.
     */
    public Square[][] createNewMap(Player player) {
        levelData.increaseLevelCounter();
        Square[][] map = initMap(levelData.getLevelSizeX(), levelData.getLevelSizeY());
        map = addStairsToMap(map);
        map = addPlayerToMap(map, player);
        List<Creature> monsters = monsterGenerator.getMonstersForLevel(levelData);
        map = populateMapWithMonsters(map, monsters);
        return map;

    }

    private Square[][] addStairsToMap(Square[][] map) {
        Square stairsSquare = pickRandomUnOccupiedSquare(map, false);
        Stairs st = new Stairs();
        st.setSquare(stairsSquare);
        stairsSquare.setObjectOnSquare(st);
        return map;
    }

    private Square[][] addPlayerToMap(Square[][] map, Player player) {
        Square playerSquare = pickRandomUnOccupiedSquare(map, true);
        playerSquare.setCreatureOnSquare(player);
        player.setSquare(playerSquare);
        return map;
    }

    private Square pickRandomUnOccupiedSquare(Square[][] map, boolean squareCanContainAWalkableObject) {
        Random rng = new Random();
        List<Square> squares = getUnoccupiedSquares(map, squareCanContainAWalkableObject);
        return squares.get(rng.nextInt(squares.size()));
    }

    // Creates a map matching the requested size
    private Square[][] initMap(int sizeX, int sizeY) {
        Square[][] newMap = new Square[sizeX][sizeY];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                Square square = new Square(x, y);
                newMap[x][y] = square;
                if (y == 0 || y == sizeY - 1 || x == 0 || x == sizeX - 1) {
                    Wall wl = new Wall();
                    wl.setSquare(square);
                    square.setObjectOnSquare(wl);
                }
            }
        }
        return newMap;
    }

    private List<Square> getUnoccupiedSquares(Square[][] map, boolean includeSquaresWithWalkableObjects) {
        List<Square> unoccupiedSquares = new ArrayList<>();
        for (Square[] squares : map) {
            for (Square square : squares) {
                if (square.getObjectOnSquare() == null && square.getCreatureOnSquare() == null) {
                    unoccupiedSquares.add(square);
                } else if (includeSquaresWithWalkableObjects && square.getObjectOnSquare() != null && square.getCreatureOnSquare() == null) {
                    if (square.getObjectOnSquare().isWalkable()) {
                        unoccupiedSquares.add(square);
                    }
                }
            }
        }
        return unoccupiedSquares;
    }

    private Square[][] populateMapWithMonsters(Square[][] map, List<Creature> monsters) {
        List<Square> squaresToPopulate = getUnoccupiedSquares(map, true);

        while (monsters.size() > squaresToPopulate.size()) { // if there are more monsters to be spawned than possible squares to spawn monsters on, remove extra monsters
            monsters.remove(monsters.size() - 1);
        }
        Random rng = new Random();
        for (Creature monster : monsters) {
            Square square = squaresToPopulate.get(rng.nextInt(squaresToPopulate.size()));
            monster.setSquare(square);
            square.setCreatureOnSquare(monster);
            squaresToPopulate.remove(square);
        }
        return map;
    }
}
