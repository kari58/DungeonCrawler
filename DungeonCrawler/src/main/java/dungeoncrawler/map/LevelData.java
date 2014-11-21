package dungeoncrawler.map;

/**
 * This class contains all data on levels, ie. how many monsters a level
 * contains, the size of a level, the difficulty of monsters within the level
 * etc. The class is only used by MapHandler which generates levels.
 *
 * @author Keke
 */
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
