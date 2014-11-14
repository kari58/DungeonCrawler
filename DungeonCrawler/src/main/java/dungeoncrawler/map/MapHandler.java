package dungeoncrawler.map;

import dungeoncrawler.creature.Creature;
import dungeoncrawler.creature.NPC;
import dungeoncrawler.creature.Player;
import dungeoncrawler.gameobject.Wall;
import dungeoncrawler.gameobject.Square;
import dungeoncrawler.gameobject.Stairs;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapHandler {

    private final LevelData levelData;
    private final MonsterGenerator monsterGenerator;

    public MapHandler() {
        this.levelData = new LevelData();
        this.monsterGenerator = new MonsterGenerator();
    }

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
        stairsSquare.setObjectOnSquare(new Stairs());
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
                    square.setObjectOnSquare(new Wall());
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
