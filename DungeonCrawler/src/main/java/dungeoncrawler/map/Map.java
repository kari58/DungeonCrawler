package dungeoncrawler.map;


import dungeoncrawler.creature.Player;
import dungeoncrawler.gameobject.Square;




public class Map {
    
    private Square[][] map;
    private MapHandler mapHandler;
    
    public Map(Player player) {
        this.mapHandler = new MapHandler();
        this.map = mapHandler.createNewMap(player);
    }
    
    public Square[][] getMap() {
        return map;
    }
    
    public void updateMapForNewLevel(Player player) {
        this.map = mapHandler.createNewMap(player);
    }

}
