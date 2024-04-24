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

import java.util.Vector;

import cos.premy.mines.GameStatus;
import cos.premy.mines.data.Mine;
import cos.premy.mines.data.MineCoord;
import cos.premy.mines.data.MineStatus;
import cos.premy.mines.data.MinesContainer;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {23})
public class GridTest {
    @Mock private MinesContainer mockMineContainer;
    @Mock private GameStatus mockGameStatus;
    @Mock private Canvas mockCanvas;
    @Mock private Mine mockMine;
    @Mock private MineField mineField;
    private Grid grid;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockMineContainer.getHeight()).thenReturn(10);
        when(mockMineContainer.getWidth()).thenReturn(10);
        when(mockGameStatus.getLevel()).thenReturn(0);
        when(mockMineContainer.getMine(anyInt(), anyInt(), anyInt())).thenReturn(null);
        when(mockMineContainer.getMine(anyInt(), anyInt(), anyInt())).thenReturn(mockMine);
        when(mockMine.getStatus()).thenReturn(MineStatus.BLOCKED);
//        when(mockMine.getNeighbors()).thenReturn(3);

        grid = new Grid(mockMineContainer, mockGameStatus);
        grid.setPosition(0, 200, 0, 100);
    }

    @Test
    public void testConstructor(){
        verify(mockMineContainer, times(1)).getHeight();
        verify(mockMineContainer, times(1)).getWidth();
    }

    @Test
    public void testDraw() {
        grid.draw(mockCanvas);
        verify(mockMineContainer, atLeastOnce()).getMine(anyInt(), anyInt(), anyInt());
        verify(mockGameStatus,times(1)).getLevel();
        verify(mockCanvas, atLeastOnce()).drawLine(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any());
    }

    @Test
    public void testSendVerifiedTap() {
        grid.sendVerifiedTap(2, 2);
        verify(mockGameStatus, times(1)).getLevel();
    }

    @Test
    public void testSendVerifiedDoubleTap() {
        grid.sendVerifiedDoubleTap(2, 2);
        verify(mockGameStatus, times(1)).getLevel();
    }

    @Test
    public void testSendVerifiedLongTap() {
        grid.sendVerifiedLongTap(2, 2);
        verify(mockGameStatus, times(1)).getLevel();
    }

    @Test
    public void testAutoFlood() {
        Vector<MineCoord> floodTargets = new Vector<>();
        floodTargets.add(new MineCoord(1, 1, 1));
        when(mockGameStatus.getFlood()).thenReturn(true);
        grid.autoFlood(floodTargets);
        verify(mockGameStatus, times(1)).getFlood();
    }

    @Test
    public void testNotAutoFlood() {
        Vector<MineCoord> floodTargets = new Vector<>();
        floodTargets.add(new MineCoord(0, 0, 0));
        when(mockGameStatus.getFlood()).thenReturn(false);
        grid.autoFlood(floodTargets);
        verify(mockGameStatus, times(1)).getFlood();
    }

    @Test
    public void testSetPosition() {
        grid.setPosition(10, 300, 20, 150);
        assertEquals(10, grid.x);
        assertEquals(20, grid.y);
        assertEquals(300, grid.width);
        assertEquals(150, grid.height);
    }
}
