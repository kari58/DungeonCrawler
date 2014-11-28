package dungeoncrawler.gameobject;

import dungeoncrawler.map.Square;

/**
 * This abstract class contains information and services common to all game
 * objects. Game objects all occupy a square, may or may not be walked through,
 * have a name and are able to draw themselves.
 */
public abstract class GameObject {

    private boolean walkable;
    private String name;
    private Square square;
    private String spritePath;

    public GameObject(boolean walkable, String name) {
        this.walkable = walkable;
        this.name = name;
    }

    public String getSpritePath() {
        return spritePath;
    }
    
    public void setSpritePath(String path) {
        this.spritePath = path;
    }
    
    public String getName() {
        return name;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }

}
