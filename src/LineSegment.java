/**
 *
 * @author Arun Kumar
 */
public class LineSegment {

    public static final double EPSILON = 0.000001;

    private Point first;
    private Point second;

    public LineSegment(Point first, Point second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return the first
     */
    public Point getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(Point first) {
        this.first = first;
    }

    /**
     * @return the second
     */
    public Point getSecond() {
        return second;
    }

    /**
     * @param second the second to set
     */
    public void setSecond(Point second) {
        this.second = second;
    }
    
     /**
     * Get the bounding box of this line by two points. The first point is in
     * the lower left corner, the second one at the upper right corner.
     *
     * @return the bounding box
     */
    public Point[] getBoundingBox() {
        Point[] result = new Point[2];
        result[0] = new Point(Math.min(first.getX(), second.getX()), Math.min(first.getY(),
                second.getY()));
        result[1] = new Point(Math.max(first.getX(), second.getX()), Math.max(first.getY(),
                second.getY()));
        return result;
    }

    /**
     * Calculate the cross product of two points.
     * @param a first point
     * @param b second point
     * @return the value of the cross product
     */
    public static double crossProduct(Point a, Point b) {
        return a.getX() * b.getY() - b.getX() * a.getY();
    }

    /**
     * Check if bounding boxes do intersect. If one bounding box
     * touches the other, they do intersect.
     * @param a first bounding box
     * @param b second bounding box
     * @return <code>true</code> if they intersect,
     *         <code>false</code> otherwise.
     */
    public static boolean doBoundingBoxesIntersect(Point[] a, Point[] b) {
        return a[0].getX() <= b[1].getY() && a[1].getX() >= b[0].getX() && a[0].getY() <= b[1].getY()
                && a[1].getY() >= b[0].getY();
    }

    /**
     * Checks if a Point is on a line
     * @param a line (interpreted as line, although given as line
     *                segment)
     * @param b point
     * @return <code>true</code> if point is on line, otherwise
     *         <code>false</code>
     */
    public static boolean isPointOnLine(LineSegment a, Point b) {
        // Move the image, so that a.first is on (0|0)
        LineSegment aTmp = new LineSegment(new Point(0, 0), new Point(
                a.second.getX() - a.first.getX(), a.second.getY() - a.first.getY()));
        Point bTmp = new Point(b.getX() - a.first.getX(), b.getY() - a.first.getY());
        double r = crossProduct(aTmp.second, bTmp);
        return Math.abs(r) < EPSILON;
    }

    /**
     * Checks if a point is right of a line. If the point is on the
     * line, it is not right of the line.
     * @param a line segment interpreted as a line
     * @param b the point
     * @return <code>true</code> if the point is right of the line,
     *         <code>false</code> otherwise
     */
    public static boolean isPointRightOfLine(LineSegment a, Point b) {
        // Move the image, so that a.first is on (0|0)
        LineSegment aTmp = new LineSegment(new Point(0, 0), new Point(
                a.second.getX() - a.first.getX(), a.second.getY() - a.first.getY()));
        Point bTmp = new Point(b.getX() - a.first.getX(), b.getY() - a.first.getY());
        return crossProduct(aTmp.second, bTmp) < 0;
    }

    /**
     * Check if line segment first touches or crosses the line that is
     * defined by line segment second.
     *
     * @param first line segment interpreted as line
     * @param second line segment
     * @return <code>true</code> if line segment first touches or
     *                           crosses line second,
     *         <code>false</code> otherwise.
     */
    public static boolean lineSegmentTouchesOrCrossesLine(LineSegment a,
            LineSegment b) {
        return isPointOnLine(a, b.first)
                || isPointOnLine(a, b.second)
                || (isPointRightOfLine(a, b.first) ^ isPointRightOfLine(a,
                        b.second));
    }

    /**
     * Check if line segments intersect
     * @param a first line segment
     * @param b second line segment
     * @return <code>true</code> if lines do intersect,
     *         <code>false</code> otherwise
     */
    public static boolean doLinesIntersect(LineSegment a, LineSegment b) {
        Point[] box1 = a.getBoundingBox();
        Point[] box2 = b.getBoundingBox();
        return doBoundingBoxesIntersect(box1, box2)
                && lineSegmentTouchesOrCrossesLine(a, b)
                && lineSegmentTouchesOrCrossesLine(b, a);
    }

    
    
}
