/**
 *
 * @author Arun Kumar
 */
public class Point {
    
    private double x;
    private double y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }
    
     /**
     * @return the y
     */
    public double getY() {
        return y;
    }
    
    public double distance(Point p)
    {
        double a = getX() - p.getX();
        double b = getY() - p.getY();
        
        return Math.sqrt(a*a + b*b);
    }

    @Override
    public String toString() {
        return "("+String.valueOf((int)x) +  "," + String.valueOf((int)y) + ")";
    }

   
}
