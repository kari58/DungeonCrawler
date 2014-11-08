package dungeoncrawler.gameobject;

public abstract class GameObject {

    // all gameobjects have a symbol which is displayed on the map
    String symbol;
    boolean walkable;

    public GameObject(boolean walkable, String symbol) {
        checkThatArgumentIsNotNull(symbol);
        this.symbol = symbol;
        this.walkable = walkable;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        checkThatArgumentIsNotNull(symbol);
        this.symbol = symbol;
        
    }
    
    private void checkThatArgumentIsNotNull(String symbol) {
        if (symbol == null) {
            throw new IllegalArgumentException ("Cannot set null as symbol for object");
        }
    }

}
