

package dungeoncrawler.gameobject;

import dungeoncrawler.creature.Creature;
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
