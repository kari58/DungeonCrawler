package dungeoncrawler.map;

import dungeoncrawler.gameobject.creature.Player;

/**
 * This class encapsulates the game map.
 */
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
    
    /**
     * Returns the width of the map.
     * 
     * @return the width of the map
     */
    public int getWidth() {
        return map.length;
    }
    
    /**
     * Returns the height of the map.
     * 
     * @return the height of the map.
     */
    public int getHeight() {
        return map[0].length;
    }

    /**
     * Calls the map handler to create a new map. This method should be called
     * once each time a player goes down a flight of stairs.
     *
     * @param player reference to the player object
     */
    public void updateMapForNewLevel(Player player) {
        this.map = mapHandler.createNewMap(player);
    }

}
