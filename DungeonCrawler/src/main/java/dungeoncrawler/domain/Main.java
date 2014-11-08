

package dungeoncrawler.domain;

import java.util.ArrayList;
import java.util.List;


public class Main {
    
    public static void main(String[] args) {
        MapHandler maphandler = new MapHandler();
        Square[][] map = maphandler.createMap(7, 12);
        List<Creature> monsters = new ArrayList<Creature>();
        Monster mobster = new Monster(true, "m", 100, 10);
        monsters.add(mobster);
        map = maphandler.populateMapWithMonsters(map, monsters);
        maphandler.drawMap();
    }

}
