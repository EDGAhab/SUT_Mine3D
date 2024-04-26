package cos.premy.mines.graphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import android.graphics.Canvas;

import cos.premy.mines.GameStatus;

public class AbstractDrawableTest {

    private AbstractDrawable drawable;
    private GameStatus gameStatus;

    @Before
    public void setUp() {
        gameStatus = mock(GameStatus.class);
        drawable = Mockito.spy(new AbstractDrawable(gameStatus) {
            @Override
            public void draw(Canvas canvas) {
                //Dummy
            }

            @Override
            protected void sendVerifiedTap(int x, int y) {
                //Dummy
            }

            @Override
            protected void sendVerifiedLongTap(int x, int y) {
                //Dummy
            }

            @Override
            protected void sendVerifiedDoubleTap(int x, int y) {
                //Dummy
            }
        });
    }

    @Test
    public void testConstructor() {
        assertSame("GameStatus should be the same as passed to the constructor.", gameStatus, drawable.gameStatus);
    }

    @Test
    public void testSetPosition() {
        drawable.setPosition(10, 50, 20, 100);
        assertEquals("X should be set to 10.", 10, drawable.x);
        assertEquals("Y should be set to 20.", 20, drawable.y);
        assertEquals("Width should be set to 50.", 50, drawable.width);
        assertEquals("Height should be set to 100.", 100, drawable.height);
    }

    @Test
    public void testVerifyTapInsideBounds() {
        drawable.setPosition(10, 50, 20, 100);
        assertTrue("Should return true when tap is inside the bounds.", drawable.verifyTap(30, 50));
    }

    @Test
    public void testVerifyTapOutsideBounds() {
        drawable.setPosition(10, 50, 20, 100);
        assertFalse("Should return false when tap is outside the bounds.", drawable.verifyTap(70, 150));
    }

    @Test
    public void testSendTapValid() {
        drawable.setPosition(10, 50, 20, 100);
        doNothing().when(drawable).sendVerifiedTap(anyInt(), anyInt());
        drawable.sendTap(30, 50);
        verify(drawable).sendVerifiedTap(30, 50);
    }

    @Test
    public void testSendTapInvalid() {
        drawable.setPosition(10, 50, 20, 100);
        drawable.sendTap(70, 150);
        verify(drawable, never()).sendVerifiedTap(70, 150);
    }

    @Test
    public void testSendLongTapValid() {
        drawable.setPosition(10, 50, 20, 100);
        doNothing().when(drawable).sendVerifiedLongTap(anyInt(), anyInt());
        drawable.sendLongTap(30, 50);
        verify(drawable).sendVerifiedLongTap(30, 50);
    }

    @Test
    public void testSendLongTapInvalid() {
        drawable.setPosition(10, 50, 20, 100);
        drawable.sendLongTap(70, 150);
        verify(drawable, never()).sendVerifiedLongTap(70, 150);
    }

    @Test
    public void testSendDoubleTapValid() {
        drawable.setPosition(10, 50, 20, 100);
        doNothing().when(drawable).sendVerifiedDoubleTap(anyInt(), anyInt());
        drawable.sendDoubleTap(30, 50);
        verify(drawable).sendVerifiedDoubleTap(30, 50);
    }

    @Test
    public void testSendDoubleTapInvalid() {
        drawable.setPosition(10, 50, 20, 100);
        drawable.sendDoubleTap(70, 150);
        verify(drawable, never()).sendVerifiedDoubleTap(70, 150);
    }
}

