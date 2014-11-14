package dungeoncrawler.map;





import dungeoncrawler.creature.Creature;
import dungeoncrawler.creature.NPC;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MonsterHandler {
    
    public List<Creature> getMonstersForLevel(LevelData levelData) {
        List<Creature> monsters = new ArrayList<>();
        int monsterAmount = levelData.getNumberOfMonstersOnLevel();
        for (int i = 0; i < monsterAmount; i++) {
            NPC newMonster = createRandomMonster(levelData);
            monsters.add(newMonster);
        }
        return monsters;
    }
    
    public NPC createRandomMonster(LevelData levelData) {
        Random rng = new Random();
        float difficultymultiplier = levelData.getMonsterDifficultyMultiplier();
        boolean isHostile = true;
        if (rng.nextDouble() < 0.33) {
            isHostile = false;
        }
        String name = getNameForMonster();
        int aggroRadius = rng.nextInt(4) + 1;
        int monsterHealth = (int) ((rng.nextInt(50) + 51) * difficultymultiplier);
        int monsterDamage = (int) ((rng.nextInt(5) + 6) * difficultymultiplier);
        String symbol = "m";
        if (!isHostile) {
            symbol = "n";
        }
        NPC monster = new NPC(name, symbol);
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
