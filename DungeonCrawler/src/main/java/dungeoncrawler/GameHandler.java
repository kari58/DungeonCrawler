package dungeoncrawler;

import dungeoncrawler.map.Map;
import dungeoncrawler.gameobject.creature.Player;

/**
 * This class contains the logic for running the game loop at the highest level.
 */
public class GameHandler {

    private Map map;
    private Player player;
    private UserInterface ui;

    public GameHandler() {
        player = new Player();
        map = new Map(player);
        ui = new UserInterface(map);
    }

    public void run() {
        ui.run();
        //runGameLoop();
        handleGameEnd();
    }


    private void handleGameEnd() {
        System.out.println("You be dead!");
    }
    /*
     private void runGameLoop() {
     while (player.getCurrentHealth() > 0) {
     ui.drawMap(map);
     player.takeDamage(10);

     }

     }*/

}
