package dungeoncrawler.logic;

import dungeoncrawler.map.Map;
import dungeoncrawler.map.Square;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class provides various utilities for interpreting relationships between
 * different squares on the map. The services here can be used to help implement
 * AI.
 */
public class MapUtils {

    private Map map;
    private Random rng;

    public MapUtils(Map map) {
        this.map = map;
        this.rng = new Random();
    }

    /**
     * This method calculates the distance between two squares. The distance is
     * calculated using only horizontal or vertical moves, diagonal moves are
     * not allowed. The method does not take into account any objects possibly
     * blocking movement between the two squares.
     *
     * @param from The square from which to calculate the distance
     * @param to The square to which the distance is calculated
     * @return The number of non-diagonal moves required to reach the target
     * square
     */
    public int getDistanceToTarget(Square from, Square to) {
        int initiatorX = from.getX();
        int initiatorY = from.getY();
        int targetX = to.getX();
        int targetY = to.getY();
        return Math.abs(initiatorX - targetX) + Math.abs(initiatorY - targetY);
    }

    /**
     * This method examines a list of squares and finds the square that is
     * closest to the specified target square. The method does not take into
     * account any objects blocking movement to the target square.
     *
     * @param squares A list of squares to be examined
     * @param targetSquare The square to which the closest square must be found
     * @return The square within the list that is closest to the target square
     */
    public Square findSquareNearestToTarget(List<Square> squares, Square targetSquare) {
        int closestDistance = 999;
        Square closestSquare = squares.get(0);
        for (Square square : squares) {
            if (getDistanceToTarget(square, targetSquare) < closestDistance) {
                closestDistance = getDistanceToTarget(square, targetSquare);
                closestSquare = square;
            }
        }
        return closestSquare;
    }

    /**
     * This method finds a list of squares that are adjacent to the specified
     * square and may currently be moved to. Only horizontally or vertically
     * adjacent squares are considered. A square is considered movable if it
     * contains no other creatures and does not contain a game object that is
     * not walkable.
     *
     * @param originalSquare The square to be examined
     * @return A list of squares that can be moved to from the original square
     */
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

    /**
     * This method examines whether a square can be moved into. A square is
     * considered movable in case it does not contain a creature, and does not
     * contain a game object which is not walkable such as a wall.
     *
     * @param square The square to be examined
     * @return Boolean value indicating whether the square can be moved into
     */
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

    /**
     * This method picks a random movable square adjacent to the specified
     * square. If there are no possible squares to move into, null is returned.
     *
     * @param originalSquare The square the adjacent squares of which are
     * examined
     * @return A randomly chosen adjacent square which can be moved into
     */
    public Square pickRandomAdjacentMovableSquare(Square originalSquare) {
        List<Square> squares = getMovableAdjacentSquares(originalSquare);
        if (squares.isEmpty()) {
            return null;
        }
        return squares.get(rng.nextInt(squares.size()));
    }

}
