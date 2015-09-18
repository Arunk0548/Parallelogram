import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Arun Kumar
 */
public class Parallelogram {

    public int findDistance(Point point1, Point point2) {
        return (int) point1.distance(point2);
    }

    /**
     * It sorts the given points (points coming in could be any order, doesn't
     * matter ) in correct order so that it can form parallelogram.
     *
     * basically this routine finds a permutation of points and check for
     * validity
	 *
	 *	Initially fix a point say [0] index,  do permutations with other three index [1,2,3] [1,3,2] [2,1,3] [ 2,3,1] [ 3,1,2] [ 3,2,1]. 
	 *  If didn't find, then fix another point say [1] index and do similar permutations. 
     *
     * @param points
     * @param printAllCombination
     */
    public void findPointsOrder(ArrayList<Point> points, boolean printAllCombination) {
        if (points == null || points.size() != 4) {

            System.out.println("Invalid input, Number of vertices should be 4");
            return;
        }

        boolean result = false;
        System.out.println("The coordinates has been given in the following order: " + points.get(0).toString() + ", "
                + points.get(1).toString() + ", " + points.get(2).toString() + ", " + points.get(3).toString());

        for (int i = 0; i < points.size(); i++) {
            ArrayList<Point> newPoints = new ArrayList<>();
            newPoints.add(points.get(i));
            //array list to hold points list, for rotation.
            ArrayList<Point> pointsToRotate = new ArrayList<Point>();

            for (int j = 0; j < points.size(); j++) {
                if (j == i) {
                    continue;
                }
                pointsToRotate.add(points.get(j));
            }

            newPoints.add(pointsToRotate.get(0));
            newPoints.add(pointsToRotate.get(1));
            newPoints.add(pointsToRotate.get(2));

            //check for initial permutation, if it is good, keep it; otherwise.
            if (IsParallelogram(newPoints)) {
                result = true;
                if (!printAllCombination) {
                    break;
                }
            }
            // check another combination by rotating last three points.
            newPoints.clear();
            newPoints.add(points.get(i));
            newPoints.add(pointsToRotate.get(0));
            newPoints.add(pointsToRotate.get(2));
            newPoints.add(pointsToRotate.get(1));
            if (IsParallelogram(newPoints)) {
                result = true;
                if (!printAllCombination) {
                    break;
                }
            }
            newPoints.clear();

            // check another combination by rotating last three points if previous
            // didn't work.
            newPoints.add(points.get(i));
            newPoints.add(pointsToRotate.get(1));
            newPoints.add(pointsToRotate.get(0));
            newPoints.add(pointsToRotate.get(2));
            if (IsParallelogram(newPoints)) {
                result = true;
                if (!printAllCombination) {
                    break;
                }
            }
        }
        if (!result) {
            System.out.println("The coordinates do not form the vertices of a parallelogram");
        }
    }

    /**
     * Validate the given points order form a parallelogram
     *
     * @param points four points
     * @return <code>true</code> if it forms a parallelogram <code>false</code>
     * if it doesn't from.
     */
    public boolean IsParallelogram(ArrayList<Point> points) {
        if (points == null || points.size() != 4) {
            return false;
        }
        Point pointA = points.get(0);
        Point pointB = points.get(1);
        Point pointC = points.get(2);
        Point pointD = points.get(3);

        //check if diagonal AC and BD intersets to each other.
        boolean result = LineSegment.doLinesIntersect(new LineSegment(pointA, pointC), new LineSegment(pointB, pointD));
        //find the lenght of all sides.
        int AB = (int) pointA.distance(pointB);
        int CD = (int) pointC.distance(pointD);
        int AD = (int) pointA.distance(pointD);
        int BC = (int) pointB.distance(pointC);
        //compare the length of oposite sides
        if (result && AB == CD && AD == BC) {
            System.out.println("The coordinates form a parallelogram in the following order: " + pointA.toString() + ", " + pointB.toString()
                    + ", " + pointC.toString() + " and " + pointD.toString());
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Scanner input = new Scanner(System.in);
            ArrayList<Point> points = new ArrayList<>();

            System.out.println("please enter first point");
            int x = input.nextInt();
            int y = input.nextInt();
            points.add(new Point(x, y));
            System.out.println("please enter second point");
            x = input.nextInt();
            y = input.nextInt();
            points.add(new Point(x, y));
            System.out.println("please enter third point");
            x = input.nextInt();
            y = input.nextInt();
            points.add(new Point(x, y));
            System.out.println("please enter fourth point");
            x = input.nextInt();
            y = input.nextInt();
            points.add(new Point(x, y));
            new Parallelogram().findPointsOrder(points, false);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please enter only numbers");
        }
    }

}
