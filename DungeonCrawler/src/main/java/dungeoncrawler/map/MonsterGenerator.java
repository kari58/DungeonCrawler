package dungeoncrawler.map;

import dungeoncrawler.gameobject.creature.Creature;
import dungeoncrawler.gameobject.creature.NPC;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class provides methods that can be used to generate monsters to be used
 * on each level.
 */
public class MonsterGenerator {

    /**
     * Generates a list of randomly created monsters. The amount of monsters to
     * be created is specified within the levelData object and depends on the
     * current map level.
     *
     * @param levelData reference to the levelData object which stores
     * information on current level and level difficulty
     * @return a list of randomly created monsters
     */
    public List<Creature> getMonstersForLevel(LevelData levelData) {
        List<Creature> monsters = new ArrayList<>();
        int monsterAmount = levelData.getNumberOfMonstersOnLevel();
        for (int i = 0; i < monsterAmount; i++) {
            NPC newMonster = createRandomMonster(levelData);
            monsters.add(newMonster);
        }
        return monsters;
    }

    /**
     * Creates a random monster. The monster has a random amount of health and
     * damage, as well as a variable aggro radius. All stats are also multiplied
     * by the level difficulty multiplier which is determined in the levelData
     * object, so that on each level monsters get tougher. On average, one third
     * of monsters are not hostile.
     *
     * @param levelData reference to the levelData object which stores
     * information on current level and level difficulty
     * @return returns an NPC object which represents a randomly generated
     * monster
     */
    public NPC createRandomMonster(LevelData levelData) {
        Random rng = new Random();
        float difficultymultiplier = levelData.getMonsterDifficultyMultiplier();
        boolean isHostile = true;
        if (rng.nextDouble() < 0.33) {
            isHostile = false;
        }
        String name = getNameForMonster();
        int aggroRadius = rng.nextInt(4) + 1;
        int monsterHealth = (int) ((rng.nextInt(51) + 50) * difficultymultiplier);
        int monsterDamage = (int) ((rng.nextInt(5) + 6) * difficultymultiplier);
        double chanceToMoveRandomly = Math.max(0.3, Math.min(0.9, rng.nextDouble()));
        String spritePath = "images\\redSquare.jpg";
        if (!isHostile) {
            spritePath = "images\\yellowSquare.jpg";
        }
        NPC monster = new NPC(name, isHostile);
        monster.setSpritePath(spritePath);
        monster.setChanceToMoveRandomly(chanceToMoveRandomly);
        monster.setHostileStatus(isHostile);
        monster.setAggroRadius(aggroRadius);
        monster.setMaxHealth(monsterHealth);
        monster.setCurrentHealth(monsterHealth);
        monster.setDamage(monsterDamage);
        return monster;
    }

    private String getNameForMonster() {
        return "monster";
    }

}
