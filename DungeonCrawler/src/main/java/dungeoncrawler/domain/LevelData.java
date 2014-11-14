

package dungeoncrawler.domain;


public class LevelData {
    
    private int currentLevel;
    
    public LevelData() {
        this.currentLevel = 0;
    }
    
    
    public int getNumberOfRoomsInLevel() {
        // Clamped value (1 = minimum, 6 = maximum)
        return Math.max(1, Math.min(6, currentLevel));
    }
    
    public void increaseLevelCounter() {
        currentLevel++;
    }
    
    public int getAmountOfMonstersOnLevel() {
        return Math.max(2, Math.min(20, currentLevel * 2));
    }
    
    public float getMonsterDifficultyMultiplier() {
        return currentLevel * 1.2f;
    }
    
    public int getLevelSizeX() {
        return Math.max(5, Math.min(30, 10 + currentLevel));
    }
    
    public int getLevelSizeY() {
        return Math.max(5, Math.min(30, 10 + currentLevel));
    }

}
