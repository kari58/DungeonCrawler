package dungeoncrawler.gameobject;

import dungeoncrawler.map.Square;

/**
 * This abstract class contains information and services common to all game
 * objects. Game objects include both creatures and static objects such as
 * walls. All game objects occupy a square, may or may not be walked through,
 * have a name and contain a path to the 2d sprite representing them in the UI.
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

    /**
     * Describes whether a creature can occupy the same square as this
     * game object.
     *
     * @return true if a creature can occupy the same square, false if not.
     */
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
