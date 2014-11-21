package dungeoncrawler.gameobject;

import dungeoncrawler.map.Square;

/**
 * This abstract class contains information and services common to all game
 * objects.
 */
public abstract class GameObject {

    String symbol;
    boolean walkable;
    String name;
    Square square;

    public GameObject(boolean walkable, String name, String symbol) {
        this.symbol = symbol;
        this.walkable = walkable;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
