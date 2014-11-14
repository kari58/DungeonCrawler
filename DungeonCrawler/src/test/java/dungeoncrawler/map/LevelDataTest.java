/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncrawler.map;

import dungeoncrawler.map.LevelData;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Keke
 */
public class LevelDataTest {
    
    private LevelData levelData;
    
    public LevelDataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        levelData = new LevelData();
    }
    
    @Test
    public void increasingLevelCounterWorks() {
        int initial = levelData.getCurrentLevel();
        for (int i = 0; i < 5; i++) {
            levelData.increaseLevelCounter();
        }
        assertEquals(5, levelData.getCurrentLevel() - initial);
    }
    
    
    
    @Test
    public void numberOfMonstersDoesNotIncreaseIndefinitely() {
        for (int i = 0; i < 100; i++) {
            levelData.increaseLevelCounter();
        }
        assertTrue(levelData.getNumberOfMonstersOnLevel() < 40);
    }
    
    @Test
    public void numberOfMonstersAlwaysIncreasesByTwo() {
        int previous = 0;
        for (int i = 0; i < 8; i++) {
            levelData.increaseLevelCounter();
            int current = levelData.getNumberOfMonstersOnLevel();
            assertEquals(current, previous + 2);
            previous = current;
        }
    }
    
    @Test
    public void monsterDifficultyIncreasesCorrectly() {
        levelData.increaseLevelCounter();
        float monsterDiff = levelData.getMonsterDifficultyMultiplier();
        levelData.increaseLevelCounter();
        assertTrue(monsterDiff < levelData.getMonsterDifficultyMultiplier());
        monsterDiff = levelData.getMonsterDifficultyMultiplier();
        levelData.increaseLevelCounter();
        assertTrue(monsterDiff < levelData.getMonsterDifficultyMultiplier());
    }
        
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
