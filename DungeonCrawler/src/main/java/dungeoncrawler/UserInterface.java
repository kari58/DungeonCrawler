package dungeoncrawler;

import dungeoncrawler.map.Square;
import dungeoncrawler.map.Map;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);

    }

    public void drawMap(Map mapObject) {
        Square[][] map = mapObject.getMap();
        for (Square[] squares : map) {
            for (Square square : squares) {
                if (square.getCreatureOnSquare() != null) {
                    System.out.print(square.getCreatureOnSquare().getSymbol());
                } else if (square.getObjectOnSquare() != null) {
                    System.out.print(square.getObjectOnSquare().getSymbol());
                } else {
                    System.out.print(square.getSymbol());
                }
            }
            System.out.println("");
        }
    }

}
