package cos.premy.mines.graphics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import static cos.premy.mines.data.MineStatus.*;

import android.graphics.Canvas;
import android.graphics.Paint;

import cos.premy.mines.GameStatus;
import cos.premy.mines.data.Mine;
import cos.premy.mines.data.MineStatus;
import cos.premy.mines.graphics.animations.Line;
import cos.premy.mines.graphics.animations.LineAnimation;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Config.OLDEST_SDK})
public class MineFieldTest {
    @Mock private Grid mockGrid;
    @Mock private Mine mockMine;
    @Mock private GameStatus mockGameStatus;
    @Mock private Canvas mockCanvas;
    @Mock private LineAnimation mockLineAnimation;

    private MineField mineField;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockMine.getStatus()).thenReturn(UNBLOCKED);
        when(mockMine.getNeighbors()).thenReturn(3);
        when(mockGameStatus.getColored()).thenReturn(true);
        when(mockGameStatus.getNumberType()).thenReturn(true);

        mineField = new MineField(mockGrid, mockMine, mockGameStatus, 0);
        mineField.setPosition(0, 100, 0, 100);
    }

    @Test
    public void testConstructor(){
        when(mockGameStatus.getColored()).thenReturn(false);
        mineField = new MineField(mockGrid, mockMine, mockGameStatus, 0);
        verify(mockGameStatus, atLeastOnce()).getColored();
        verify(mockGameStatus, atLeastOnce()).getNumberType();
    }



    @Test
    public void testConstructorWithColored(){
        when(mockGameStatus.getColored()).thenReturn(true);

        verify(mockGameStatus, times(1)).getColored();
        verify(mockGameStatus, times(1)).getNumberType();
    }

    @Test
    public void testDrawOPENEDNoColor() {

        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getColored()).thenReturn(false);
        mineField = new MineField(mockGrid, mockMine, mockGameStatus, 0);
        mineField.setPosition(10, 20, 30, 40);
        mineField.draw(mockCanvas);
        verify(mockCanvas, times(7)).drawLine(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any(Paint.class));
        verify(mockMine, times(2)).getNeighbors();
    }

    @Test
    public void testDrawNotOPENEDColor() {

        when(mockMine.getStatus()).thenReturn(UNBLOCKED);
        when(mockGameStatus.getColored()).thenReturn(true);
        mineField.draw(mockCanvas);
        verify(mockCanvas, times(2)).drawLine(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any(Paint.class));
        verify(mockMine, never()).getNeighbors();
    }

    @Test
    public void testDrawNotOPENEDNoColor() {
        when(mockMine.getStatus()).thenReturn(UNBLOCKED);
        when(mockGameStatus.getColored()).thenReturn(false);
        mineField = new MineField(mockGrid, mockMine, mockGameStatus, 0);
        mineField.setPosition(10, 20, 30, 40);
        mineField.draw(mockCanvas);
        verify(mockCanvas, times(2)).drawLine(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any(Paint.class));
    }

    @Test
    public void testDrawOPENEDColoredNeighborsOne() {

        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getColored()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(1);
        mineField.draw(mockCanvas);
        verify(mockMine, times(1)).getNeighbors();
        verify(mockCanvas, times(2)).drawLine(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any(Paint.class));


    }

    @Test
    public void testDrawOPENEDColoredNeighborsTwo() {

        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getColored()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(2);
        mineField.draw(mockCanvas);
        verify(mockMine, times(1)).getNeighbors();
        verify(mockCanvas, times(2)).drawLine(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any(Paint.class));


    }
    @Test
    public void testDrawOPENEDColoredNeighborsThree() {

        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getColored()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(3);
        mineField.draw(mockCanvas);
        verify(mockMine, times(1)).getNeighbors();
        verify(mockCanvas, times(2)).drawLine(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any(Paint.class));


    }
    @Test
    public void testDrawOPENEDColoredNeighborsFour() {

        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getColored()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(4);
        mineField.draw(mockCanvas);
        verify(mockMine, times(1)).getNeighbors();
        verify(mockCanvas, times(2)).drawLine(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any(Paint.class));


    }
    @Test
    public void testDrawOPENEDColoredNeighborsFive() {

        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getColored()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(5);
        mineField.draw(mockCanvas);
        verify(mockMine, times(1)).getNeighbors();
        verify(mockCanvas, times(2)).drawLine(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any(Paint.class));


    }

    @Test
    public void testDrawOPENEDColoredNeighborsSix() {

        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getColored()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(6);
        mineField.draw(mockCanvas);
        verify(mockMine, times(1)).getNeighbors();
        verify(mockCanvas, times(2)).drawLine(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any(Paint.class));


    }


    @Test
    public void testHandleLongTapUNBLOCKED() {
        when(mockMine.getStatus()).thenReturn(UNBLOCKED);
        mineField.sendVerifiedLongTap(50, 50);
        verify(mockMine, atLeastOnce()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
    }

    @Test
    public void testSendVerifiedTapBLOCKED() {
        when(mockMine.getStatus()).thenReturn(BLOCKED);
        mineField.sendVerifiedLongTap(50, 50);
        verify(mockMine, atLeastOnce()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        assertNotNull(mineField.getMinesLines());
        assertNotNull(mineField.getCrossLines());
    }

    @Test
    public void testSendVerifiedTapOPENEDNumberTypeTrueNeighborsZero() {
        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getNumberType()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(0);
        mineField.sendVerifiedLongTap(50, 50);
        verify(mockMine, never()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockMine, times(1)).getNeighbors();
    }

    @Test
    public void testSendVerifiedTapOPENEDNumberTypeTrueNeighborsOne() {
        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getNumberType()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(1);
        mineField.sendVerifiedLongTap(50, 50);
        verify(mockMine, never()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));

    }

    @Test
    public void testSendVerifiedTapOPENEDNumberTypeTrueNeighborsTwo() {
        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getNumberType()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(2);
        mineField.sendVerifiedLongTap(50, 50);
        verify(mockMine, never()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockMine, times(2)).getNeighbors();
    }

    @Test
    public void testSendVerifiedTapOPENEDNumberTypeTrueNeighborsThree() {
        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getNumberType()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(3);
        mineField.sendVerifiedLongTap(50, 50);
        verify(mockMine, never()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockMine, times(2)).getNeighbors();
    }

    @Test
    public void testSendVerifiedTapOPENEDNumberTypeTrueNeighborsFour() {
        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getNumberType()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(4);
        mineField.sendVerifiedLongTap(50, 50);
        verify(mockMine, never()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockMine, times(2)).getNeighbors();
    }

    @Test
    public void testSendVerifiedTapOPENEDNumberTypeTrueNeighborsFive() {
        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getNumberType()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(5);
        mineField.sendVerifiedLongTap(50, 50);
        verify(mockMine, never()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockMine, times(2)).getNeighbors();
    }

    @Test
    public void testSendVerifiedTapOPENEDNumberTypeTrueNeighborsSix() {
        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getNumberType()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(6);
        mineField.sendVerifiedLongTap(50, 50);
        verify(mockMine, never()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockMine, times(2)).getNeighbors();
    }

    @Test
    public void testSendVerifiedTapOPENEDNumberTypeFalse() {
        when(mockMine.getStatus()).thenReturn(OPENED);
        when(mockGameStatus.getNumberType()).thenReturn(false);
        when(mockMine.getNeighbors()).thenReturn(3);
        mineField = new MineField(mockGrid, mockMine, mockGameStatus, 0);
        mineField.setPosition(10, 20, 30, 40);
        mineField.sendVerifiedLongTap(50, 50);
        verify(mockMine, never()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockMine, times(16)).getNeighbors();
    }

    @Test
    public void testSendVerifiedTapUNBLOCKED() {
        when(mockMine.getStatus()).thenReturn(UNBLOCKED);

        mineField.sendVerifiedLongTap(50, 50);
        verify(mockMine, times(3)).getStatus();
        verify(mockMine, atLeastOnce()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
    }

    @Test
    public void testSendVerifiedTapOPENED() {
        when(mockMine.getStatus()).thenReturn(OPENED);

        mineField.sendVerifiedLongTap(50, 50);
        verify(mockMine, times(3)).getStatus();
        verify(mockMine, never()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
    }

    @Test
    public void testSendVerifiedDoubleTapUNBLOCKED(){
        when(mockMine.getStatus()).thenReturn(UNBLOCKED);
        when(mockGameStatus.getFlood()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(0);
        mineField.sendVerifiedDoubleTap(50,50);
        verify(mockMine, times(1)).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockGrid, times(1)).autoFlood(any());
        verify(mockMine, times(1)).getAllNeighborCoords();
    }

    @Test
    public void testSendVerifiedDoubleTapUNBLOCKEDNoFlood(){
        when(mockMine.getStatus()).thenReturn(UNBLOCKED);
        when(mockGameStatus.getFlood()).thenReturn(false);
        when(mockMine.getNeighbors()).thenReturn(0);
        mineField.sendVerifiedDoubleTap(50,50);
        verify(mockMine, times(1)).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockGameStatus, times(1)).getFlood();
        verify(mockMine, never()).getNeighbors();
        verify(mockGrid, never()).autoFlood(any());
        verify(mockMine, never()).getAllNeighborCoords();
    }

    @Test
    public void testSendVerifiedDoubleTapUNBLOCKEDNeighborNotZero(){
        when(mockMine.getStatus()).thenReturn(UNBLOCKED);
        when(mockGameStatus.getFlood()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(1);
        mineField.sendVerifiedDoubleTap(50,50);
        verify(mockMine, times(1)).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockGameStatus, times(1)).getFlood();
        verify(mockMine, times(1)).getNeighbors();
        verify(mockGrid, never()).autoFlood(any());
        verify(mockMine, never()).getAllNeighborCoords();
    }



    @Test
    public void testSendVerifiedDoubleTapBLOCKED(){
        when(mockMine.getStatus()).thenReturn(BLOCKED);
        mineField.sendVerifiedDoubleTap(50,50);
        verify(mockMine, never()).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockGrid, never()).autoFlood(any());
    }

    @Test
    public void testSendVerifiedDoubleTapUNBLOCKEDNotFlood(){
        when(mockMine.getStatus()).thenReturn(UNBLOCKED);
        when(mockGameStatus.getFlood()).thenReturn(false);
        when(mockMine.getNeighbors()).thenReturn(0);
        mineField.sendVerifiedDoubleTap(50,50);
        verify(mockMine, times(1)).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockGrid, never()).autoFlood(any());
    }

    @Test
    public void testSendVerifiedDoubleTapUNBLOCKEDNeighbors(){
        when(mockMine.getStatus()).thenReturn(UNBLOCKED);
        when(mockGameStatus.getFlood()).thenReturn(true);
        when(mockMine.getNeighbors()).thenReturn(1);
        mineField.sendVerifiedDoubleTap(50,50);
        verify(mockMine, times(1)).setGameStatus(any(MineStatus.class), eq(mockGameStatus));
        verify(mockGrid, never()).autoFlood(any());
    }

    @Test
    public void testSetPosition(){
        mineField.setPosition(0, 20, 0,20);
        assertEquals(2, mineField.getCrossLines().length);
        assertEquals(5, mineField.getMinesLines().length);
        assertEquals(2, mineField.getCrossLinesAnimation().length);
        assertEquals(5, mineField.getMinesLinesAnimation().length);
    }




}

