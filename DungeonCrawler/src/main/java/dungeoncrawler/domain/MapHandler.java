package dungeoncrawler.domain;

import dungeoncrawler.creature.Creature;
import dungeoncrawler.creature.NPC;
import dungeoncrawler.gameobject.Wall;
import dungeoncrawler.gameobject.Square;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapHandler {

    private Square[][] map;
    private final LevelData levelData;

    public MapHandler() {
        this.levelData = new LevelData();
    }

    public void createNewMap() {
        levelData.increaseLevelCounter();
        initMap(levelData.getLevelSizeX(), levelData.getLevelSizeY());
        addRoomsToMap();
        List<Creature> monsters = getMonstersForLevel();

    }

    public void drawMap() {
        for (Square[] squares : map) {
            for (Square square : squares) {
                if (square.getCreatureOnSquare() != null) {
                    System.out.print(square.getCreatureOnSquare().getSymbol());
                } else if (square.getObjectOnSquare() != null) {
                    System.out.print(square.getObjectOnSquare().getSymbol());
                } else {
                    System.out.print(square.getSymbol());
                }
            }
            System.out.println("");
        }
    }

    private void addRoomsToMap() {

    }

    // Creates a map matching the requested size
    private void initMap(int sizeX, int sizeY) {
        if (sizeX > 25 || sizeY > 25) {
            throw new IllegalArgumentException("Requested map size is too large");
        }
        if (sizeX < 5 || sizeY < 5) {
            throw new IllegalArgumentException("Requested map size too small");
        }
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
        this.map = newMap;
    }

    // assigns a unique square for every monster, note that this method does not ensure that the
    // squares do not already contain something else, this needs to be done in the calling method
    private Square[][] populateMapWithMonsters(Square[][] newMap, List<Creature> monsters) {
        List<Square> squaresToPopulate = new ArrayList<Square>();
        // iterate through every square in the map and add each square to a list from which a square can be randomly assigned to spawned monsters
        for (Square[] squares : newMap) {
            for (Square square : squares) {
                squaresToPopulate.add(square);
            }
        }
        if (monsters.size() > squaresToPopulate.size()) {
            throw new IllegalArgumentException("Amount of monsters to spawn cannot exceed the amount of squares available on the map");
        }
        Random rng = new Random();
        for (Creature monster : monsters) {
            Square square = squaresToPopulate.get(rng.nextInt(squaresToPopulate.size()));
            monster.setSquare(square);
            square.setCreatureOnSquare(monster);
            squaresToPopulate.remove(square);
        }
        this.map = newMap;
        return this.map;
    }

    private List<Creature> getMonstersForLevel() {
        List<Creature> monsters = new ArrayList<>();
        int monsterAmount = levelData.getAmountOfMonstersOnLevel();

        for (int i = 0; i < monsterAmount; i++) {
            NPC mob = createRandomMonster();

        }
        return monsters;
    }

    private NPC createRandomMonster() {
        Random rng = new Random();
        float difficultymultiplier = levelData.getMonsterDifficultyMultiplier();
        boolean isHostile = true;
        if (rng.nextDouble() < 0.33) {
            isHostile = false;
        }
        String name = getNameForMonster();
        int aggroRadius = rng.nextInt(4) + 1;
        int monsterHealth = (int) ((rng.nextInt(50) + 51) * difficultymultiplier);
        int monsterDamage = (int) ((rng.nextInt(5) + 6) * difficultymultiplier);
        String symbol = "m";
        if (!isHostile) {
            symbol = "n";
        }
        NPC monster = new NPC(name, symbol);
        return monster;
    }
    
    private String getNameForMonster() {
        return "monster";
    }

}
