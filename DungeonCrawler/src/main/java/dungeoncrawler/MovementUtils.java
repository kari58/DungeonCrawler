

package dungeoncrawler;

import dungeoncrawler.map.Map;
import dungeoncrawler.map.Square;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MovementUtils {
    
    private Map map;
    private Random rng;
    
    public MovementUtils(Map map) {
        this.map = map;
        this.rng = new Random();
    }
    
    
    public int getDistanceToTarget(Square from, Square to) {
        int initiatorX = from.getX();
        int initiatorY = from.getY();
        int targetX = to.getX();
        int targetY = to.getY();
        return Math.abs(initiatorX - targetX) + Math.abs(initiatorY - targetY);
    }

    public Square findSquareNearestToTarget(List<Square> squares, Square targetSquare) {
        int closestDistance = 999;
        Square closestSquare = squares.get(0);
        for (Square square : squares) {
            if (getDistanceToTarget(square, targetSquare) < closestDistance) {
                closestDistance = getDistanceToTarget(square,targetSquare);
                closestSquare = square;
            }
        }
        return closestSquare;
    }
    
    public List<Square> getMovableAdjacentSquares(Square originalSquare) {
        int squareX = originalSquare.getX();
        int squareY = originalSquare.getY();
        List<Square> squares = new ArrayList<Square>();
        if (squareX - 1 >= 0) {
            if (squareIsMovable(map.getMap()[squareX - 1][squareY])) {
                squares.add(map.getMap()[squareX - 1][squareY]);
            }
        }
        if (squareX + 1 < map.getWidth()) {
            if (squareIsMovable(map.getMap()[squareX + 1][squareY])) {
                squares.add(map.getMap()[squareX + 1][squareY]);
            }
        }
        if (squareY - 1 >= 0) {
            if (squareIsMovable(map.getMap()[squareX][squareY - 1])) {
                squares.add(map.getMap()[squareX][squareY - 1]);
            }
        }
        if (squareY + 1 < map.getHeight()) {
            if (squareIsMovable(map.getMap()[squareX][squareY + 1])) {
                squares.add(map.getMap()[squareX][squareY + 1]);
            }
        }
        return squares;
    }
    
    public boolean squareIsMovable(Square square) {
        if (square.getCreatureOnSquare() != null) {
            return false;
        }
        if (square.getObjectOnSquare() != null) {
            if (!square.getObjectOnSquare().isWalkable()) {
                return false;
            }
        }
        return true;
    }

    public Square pickRandomSquareToMoveTo(Square originalSquare) {
        List<Square> squares = getMovableAdjacentSquares(originalSquare);
        if (squares.isEmpty()) {
            return null;
        }
        return squares.get(rng.nextInt(squares.size()));
    }

}
