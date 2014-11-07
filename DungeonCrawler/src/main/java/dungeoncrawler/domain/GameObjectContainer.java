

package dungeoncrawler.domain;

import java.util.List;


public class GameObjectContainer {
    
    private Square[][] map;
    private List<Creature> monsters;
    
    private Square[][] getMap() {
        return map;
    }
    
    private List<Creature> getMonsters() {
        return monsters;
    }

}
