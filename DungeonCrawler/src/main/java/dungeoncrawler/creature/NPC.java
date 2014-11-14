package dungeoncrawler.creature;






public class NPC extends Creature {
        
    private boolean isHostile;
    private int aggroRadius;
    
    public NPC(String name, String symbol) {
        super(name, symbol);
        this.isHostile = true;
        this.aggroRadius = 2;
    }

    @Override
    public void move() {
    }
    
    public void setAggroRadius(int radius) {
        aggroRadius = radius;       
    }
    
    public int getAggroRadius() {
        return aggroRadius;
    }
    
    public void setHostileStatus(boolean hostileStatus) {
        this.isHostile = hostileStatus;
    }

    public boolean isHostile() {
        return isHostile;
    }

}
