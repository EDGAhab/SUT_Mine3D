package cos.premy.mines.graphics.animations;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import cos.premy.mines.Utils;

public class LineTest {
    private Line line;

    @Before
    public void setUp() {
        line = new Line(new Point(0, 0), new Point(3, 4));
    }

    @Test
    public void testLineCreation() {
        Point point1 = new Point(0,0);
        Point point2 = new Point(3,4);
        line = new Line(point1, point2);
        assertEquals(point1, line.start);

        assertEquals(point2, line.end);
    }



    @Test
    public void testLineCreationWithInts() {
        Line intLine = new Line(1, 4, 1, 5);
        assertEquals(1, intLine.start.X);
        assertEquals(1, intLine.start.Y);
        assertEquals(4, intLine.end.X);
        assertEquals(5, intLine.end.Y);
    }

    @Test
    public void testChangeLength() {
        line.changeLength(10);
        double newLength = Math.sqrt(Math.pow(line.end.X - line.start.X, 2) + Math.pow(line.end.Y - line.start.Y, 2));
        assertEquals(10, newLength, 0.01);
        assertNotNull(line.end);

    }


}
