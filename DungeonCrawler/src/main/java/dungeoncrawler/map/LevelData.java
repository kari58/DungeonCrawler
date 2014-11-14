package dungeoncrawler.map;






public class LevelData {
    
    private int currentLevel;
    
    public LevelData() {
        this.currentLevel = 0;
    }
    
    
    public void increaseLevelCounter() {
        currentLevel++;
    }
    
    public int getCurrentLevel() {
        return currentLevel;
    }
    
    public int getNumberOfMonstersOnLevel() {
        return Math.max(2, Math.min(20, currentLevel * 2));
    }
    
    public float getMonsterDifficultyMultiplier() {
        return 1 + (currentLevel * 1.2f / 10);
    }
    
    public int getLevelSizeX() {
        return Math.max(5, Math.min(30, 10 + currentLevel));
    }
    
    public int getLevelSizeY() {
        return Math.max(5, Math.min(30, 10 + currentLevel));
    }

}
