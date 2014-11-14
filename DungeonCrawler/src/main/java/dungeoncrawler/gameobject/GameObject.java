package dungeoncrawler.gameobject;



public abstract class GameObject {

    // all gameobjects have a symbol which is displayed on the map
    String symbol;
    boolean walkable;
    String name;

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

    
}
