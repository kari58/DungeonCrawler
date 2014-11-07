

package dungeoncrawler.domain;

import java.util.List;


public class MapHandler {
    
    // Creates a map matching the requested size
    public Square[][] createMap(int sizeX, int sizeY) {
        if (sizeX > 25 || sizeY > 25) {
            throw new IllegalArgumentException("Requested map size is too large");
        }
        if (sizeX < 5 || sizeY < 5) {
            throw new IllegalArgumentException("Requested map size too small");
        }
        Square[][] map = new Square[sizeX][sizeY];
        
        
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                Square square = new Square(x, y);
                map[x][y] = square;
            }
        }
        return map;
    }
    
    public Square[][] populateMapWithMonsters(Square[][] map, List<Creature> monsters) {
        Square[][] squaresToPopulate = map.clone();
        return map;
    }

}
