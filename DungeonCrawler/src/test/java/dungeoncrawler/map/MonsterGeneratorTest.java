/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncrawler.map;

import dungeoncrawler.gameobject.creature.NPC;
import dungeoncrawler.map.LevelData;
import dungeoncrawler.map.MonsterGenerator;
import java.util.ArrayList;
import java.util.List;
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
public class MonsterGeneratorTest {

    private MonsterGenerator mh;
    private LevelData ld;

    public MonsterGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mh = new MonsterGenerator();
        ld = new LevelData();
        ld.increaseLevelCounter();
    }

    @Test
    public void statsAreRandomized() {
        List<NPC> monsters = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            monsters.add(mh.createRandomMonster(ld));
        }
        boolean healthVaries = false;
        boolean hostileStatusVaries = false;
        boolean damageVaries = false;
        boolean aggroRadiusVaries = false;
        int firstHealth = monsters.get(0).getMaxHealth();
        int firstAggroRadius = monsters.get(0).getAggroRadius();
        int firstDamage = monsters.get(0).getDamage();
        boolean firstHostileStatus = monsters.get(0).isHostile();
        for (NPC monster : monsters) {
            int currentHealth = monster.getMaxHealth();
            int currentAggroRadius = monster.getAggroRadius();
            int currentDamage = monster.getDamage();
            boolean currentHostileStatus = monster.isHostile();
            if (currentHealth != firstHealth) {
                healthVaries = true;
            }
            if (currentAggroRadius != firstAggroRadius) {
                aggroRadiusVaries = true;
            }
            if (currentDamage != firstDamage) {
                damageVaries = true;
            }
            if (currentHostileStatus != firstHostileStatus) {
                hostileStatusVaries = true;
            }
        }
        assertTrue(healthVaries && aggroRadiusVaries && damageVaries && hostileStatusVaries);
    }

    @Test
    public void generatedHealthIsWithinDifficultyMultiplierRange() {
        for (int i = 0; i < 100; i++) {
            NPC monster = mh.createRandomMonster(ld);
            assertTrue(monster.getMaxHealth() > 49 * ld.getMonsterDifficultyMultiplier());
            assertTrue(monster.getMaxHealth() < 101 * ld.getMonsterDifficultyMultiplier());
        }

    }
    
    @Test
    public void generatedDamageIsWithinDifficultyMultiplierRange() {
        for (int i = 0; i < 30; i++) {
            NPC monster = mh.createRandomMonster(ld);
            assertTrue(monster.getDamage() > 5 * ld.getMonsterDifficultyMultiplier());
            assertTrue(monster.getDamage() < 11 * ld.getMonsterDifficultyMultiplier());
        }
    }
    
    @Test
    public void generatedAggroRadiusIsWithinCorrectRange() {
        for (int i = 0; i < 25; i++) {
            NPC monster = mh.createRandomMonster(ld);
            assertTrue(monster.getAggroRadius() > 0);
            assertTrue(monster.getAggroRadius() < 5);
        }
    }
    
    /*
    @Test
    public void monstersAlwaysHaveCorrectSymbolDependingOnHostileStatus() {
        for (int i = 0; i < 50; i++) {
            NPC monster = mh.createRandomMonster(ld);
            if (monster.isHostile()) {
                assertEquals("m", monster.getSymbol());
            }
            else {
                assertEquals("n", monster.getSymbol());
            }
        }
    }*/
    

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
