package dungeoncrawler;

import dungeoncrawler.map.Map;
import dungeoncrawler.creature.Player;

public class GameHandler {

    private Map map;
    private Player player;
    private UserInterface ui;
    
    public GameHandler() {
        player = new Player();
        map = new Map(player);
        ui = new UserInterface();
    }

    public void run() {
        initGame();
        runGameLoop();
    }

    private void initGame() {
        
    }

    private void runGameLoop() {
        while (player.getCurrentHealth() > 0) {
            ui.drawMap(map);
            player.takeDamage(10);
        }
        System.out.println("You be dead!");
    }

}