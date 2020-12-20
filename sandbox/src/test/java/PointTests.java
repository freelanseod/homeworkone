import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void checkDistanceWithCorrectValues() {
        Point pOne = new Point(0, 0);
        Point pTwo = new Point(10, 10);
        Assert.assertEquals(pOne.distance(pTwo), 14.142135623730951);
    }

    @Test
    public void checkDistanceWithZeroValues() {
        Point pOne = new Point(0, 0);
        Point pTwo = new Point(0, 0);
        Assert.assertEquals(pOne.distance(pTwo), 0);
    }

    @Test
    public void checkCorrectlyCalculatedDistance() {
        Point pOne = new Point(-10, -200);
        Point pTwo = new Point(-10, 200);
        Assert.assertNotEquals(pOne.distance(pTwo), 0);
    }

}
