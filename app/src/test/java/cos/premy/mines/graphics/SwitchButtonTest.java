package cos.premy.mines.graphics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.graphics.Canvas;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import cos.premy.mines.GameStatus;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {23})
public class SwitchButtonTest {
    @Mock private GameStatus mockGameStatus;
    @Mock private Canvas mockCanvas;
    private SwitchButton switchButton;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockGameStatus.getLevel()).thenReturn(0);
        switchButton = new SwitchButton(mockGameStatus);
        switchButton.setPosition(0, 200, 0, 100);
    }

    @Test
    public void testDraw() {
        switchButton.draw(mockCanvas);
        verify(mockCanvas, times(6)).drawLine(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any());
    }

    @Test
    public void testHandleTap() {
        switchButton.sendVerifiedTap(50, 50);
        verify(mockGameStatus, times(1)).switchLevel();

    }

    @Test
    public void testSetPositionLevelOneWidthGreaterThanHeight() {
        when(mockGameStatus.getLevel()).thenReturn(1);
        switchButton.setPosition(0, 200, 0, 100);
        verify(mockGameStatus,times(2)).getLevel();
    }

    @Test
    public void testSetPositionLevelZero() {
        when(mockGameStatus.getLevel()).thenReturn(0);
        switchButton.setPosition(0, 100, 0, 200);
        verify(mockGameStatus,times(2)).getLevel();
    }
}
