public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2) {
        return Math.sqrt((p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
    }

}

class CheckPoint {
    public static void main(String[] args) {
        Point p1 = new Point(0,0);
        Point p2 = new Point(10,10);
        System.out.println("distance " + p1.distance(p2));
    }

}
