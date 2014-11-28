package dungeoncrawler.map;

/**
 * This class contains all data on what a map level consists of. The class
 * describes how many monsters a level contains, the size of a level, the
 * difficulty of monsters within the level and any other information that
 * affects the makeup of a level.
 *
 */
public class LevelData {

    private int currentLevel;
    private int maxMonsters;
    private int levelMaxSizeX;
    private int levelMaxSizeY;

    public LevelData() {
        this.currentLevel = 0;
        maxMonsters = 20;
        levelMaxSizeX = 30;
        levelMaxSizeY = 30;
    }

    /**
     * Increases the current level counter by one. This should be called once
     * each time a new level is generated.
     */
    public void increaseLevelCounter() {
        currentLevel++;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Returns the number of monsters that should be generated on the current
     * level. The number of monsters should increase by 2 per level, up to a
     * maximum specified in maxMonsters variable.
     *
     * @return The number of monsters to be generated for the level
     */
    public int getNumberOfMonstersOnLevel() {
        return Math.max(2, Math.min(maxMonsters, currentLevel * 2));
    }

    /**
     * Determines the difficulty multiplier as monsters get tougher each level.
     * The difficulty multiplier can be used to multiply monsters' base stats to
     * obtain tougher monsters each level.
     *
     * @return The difficulty multiplier for the current map level.
     */
    public float getMonsterDifficultyMultiplier() {
        return 1 + (currentLevel * 1.2f / 10);
    }

    /**
     * Returns the X-dimension size of the current level. The size should be 10
     * + the current level counter up to a limit specified in levelMaxSizeX.
     *
     * @return the X-dimension size of the current level
     */
    public int getLevelSizeX() {
        return Math.max(5, Math.min(levelMaxSizeX, 10 + currentLevel));
    }

    /**
     * Returns the Y-dimension size of the current level. The size should be 10
     * + the current level counter up to a limit specified in levelMaxSizeY.
     *
     * @return The Y-dimension size of the current level
     */
    public int getLevelSizeY() {
        return Math.max(5, Math.min(levelMaxSizeY, 10 + currentLevel));
    }

}
