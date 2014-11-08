

package dungeoncrawler.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MapHandler {
    
    private Square[][] map;
    
    
    public void drawMap() {
        for (Square[] squares : map) {
            for (Square square : squares) {
                if (square.getCreatureOnSquare() != null) {
                    System.out.print(square.getCreatureOnSquare().getSymbol());
                }
                else if (square.getObjectOnSquare() != null) {
                    System.out.print(square.getObjectOnSquare().getSymbol());
                }
                else {
                    System.out.print(square.getSymbol());
                }
            }
            System.out.println("");
        }
    }
    
    // Creates a map matching the requested size
    public Square[][] createMap(int sizeX, int sizeY) {
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
        return this.map;
    }
    
    // assigns a unique square for every monster, note that this method does not ensure that the
    // squares do not already contain something else, this needs to be done in the calling method
    public Square[][] populateMapWithMonsters(Square[][] newMap, List<Creature> monsters) {
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
        // pick a unique random square for every monster
        // note that this method does not make any assumption that 
        for (Creature monster : monsters) {
            Square square = squaresToPopulate.get(rng.nextInt(squaresToPopulate.size()));
            monster.setSquare(square);
            square.setCreatureOnSquare(monster);
            squaresToPopulate.remove(square);
        }
        this.map = newMap;
        return this.map;
    }
    

}
