package cos.premy.mines.graphics.animations;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LinearLineAnimationTest {
    private LinearLineAnimation animation;
    private Line startLine;
    private Line endLine;
    private int duration;

    @Before
    public void setUp() {
        startLine = new Line(new Point(0, 0), new Point(10, 10));
        endLine = new Line(new Point(100, 100), new Point(110, 110));
        duration = 1000; // 1000 milliseconds for the animation duration
        animation = new LinearLineAnimation(startLine, endLine, duration);
    }

    @Test
    public void testAnimationStart() {
        // Test at the beginning of the animation
        simulateTimePassed(0);
        Line result = animation.getLine();
        assertEquals(startLine.start.X, result.start.X);
        assertEquals(startLine.start.Y, result.start.Y);
        assertEquals(startLine.end.X, result.end.X);
        assertEquals(startLine.end.Y, result.end.Y);
    }

    @Test
    public void testAnimationMiddle() {
        // Test at the middle of the animation
        simulateTimePassed(duration / 2);
        Line result = animation.getLine();
        Point expectedStart = new Point(
                startLine.start.X + (endLine.start.X - startLine.start.X) / 2,
                startLine.start.Y + (endLine.start.Y - startLine.start.Y) / 2
        );
        Point expectedEnd = new Point(
                startLine.end.X + (endLine.end.X - startLine.end.X) / 2,
                startLine.end.Y + (endLine.end.Y - startLine.end.Y) / 2
        );
        assertEquals(expectedStart.X, result.start.X);
        assertEquals(expectedStart.Y, result.start.Y);
        assertEquals(expectedEnd.X, result.end.X);
        assertEquals(expectedEnd.Y, result.end.Y);
    }

    @Test
    public void testAnimationEnd() {
        // Test at the end of the animation
        simulateTimePassed(duration);
        Line result = animation.getLine();
        assertEquals(endLine.start.X, result.start.X);
        assertEquals(endLine.start.Y, result.start.Y);
        assertEquals(endLine.end.X, result.end.X);
        assertEquals(endLine.end.Y, result.end.Y);
    }

    private void simulateTimePassed(long millis) {
        // Mock the passage of time
        animation.startTime = System.currentTimeMillis() - millis;
    }
}
