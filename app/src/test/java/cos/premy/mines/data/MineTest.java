package cos.premy.mines.data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static cos.premy.mines.data.MineStatus.*;

import cos.premy.mines.GameStatus;
import cos.premy.mines.MyHappyException;

public class MineTest {
    private Mine mine;

    @Mock
    private GameStatus mockGameStatus;
    @Mock
    private MineStatusChangedListener mockListener;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mine = new Mine(true);
        mine.addMineStatusChangedListener(mockListener);
    }

    @Test
    public void testConstructorAndGetIsReal() {
        Mine mine = new Mine(true);
        assertTrue(mine.getIsReal());
        assertEquals(UNBLOCKED, mine.getStatus());
        assertEquals(0, mine.getNeighbors());

        mine = new Mine(false);
        assertFalse(mine.getIsReal());
    }

    @Test(expected = MyHappyException.class)
    public void testSetIsRealWithException() throws MyHappyException {
        Mine mine = new Mine();
        mine.setFactorized();
        mine.setIsReal(true);
    }

    @Test
    public void testSetIsRealWithoutException() throws MyHappyException {
        Mine mine = new Mine();
        mine.setIsReal(true);
        assertTrue( mine.getIsReal());
    }

    @Test
    public void testSetAndGetAllNeighborCoords() {
        Mine mine = new Mine();
        MineCoord coord1 = new MineCoord(1, 1,1);
        MineCoord coord2 = new MineCoord();
        mine.setNeighborCoord(coord1);
        mine.setNeighborCoord(coord2);

        assertTrue(mine.getAllNeighborCoords().contains(coord1));
        assertEquals(2, mine.getAllNeighborCoords().size());
    }

    @Test
    public void testSetNeighborMineCoordAndGetNeighbors() {
        Mine mine = new Mine();
        MineCoord coord = new MineCoord(1, 1, 1);
        mine.setNeighborMineCoord(coord);

        assertEquals(1, mine.getNeighbors());
    }


    @Test(expected = MyHappyException.class)
    public void testSetStatusWithException() throws MyHappyException {
        Mine mine = new Mine();
        mine.setFactorized();
        mine.setStatus(BLOCKED);
    }

    @Test
    public void testSetStatusWithoutException() throws MyHappyException {
        Mine mine = new Mine();
        mine.setStatus(BLOCKED);
        assertEquals(BLOCKED, mine.getStatus());
    }

    @Test
    public void testSetGameStatusOPENED() {
        Mine mine = new Mine();
        mine.addMineStatusChangedListener(mockListener);

        mine.setGameStatus(OPENED, mockGameStatus);
        assertEquals(MineStatus.OPENED, mine.getStatus());
        verify(mockListener, times(1)).onOpen(mockGameStatus, mine);
        verify(mockListener, never()).onBlock(mockGameStatus, mine);
        verify(mockListener, never()).onUnblock(mockGameStatus, mine);
    }

    @Test
    public void testSetGameStatusUNBLOCKED() {
        Mine mine = new Mine();
        mine.addMineStatusChangedListener(mockListener);

        mine.setGameStatus(UNBLOCKED, mockGameStatus);
        assertEquals(UNBLOCKED, mine.getStatus());
        verify(mockListener, never()).onOpen(mockGameStatus, mine);
        verify(mockListener, never()).onBlock(mockGameStatus, mine);
        verify(mockListener, times(1)).onUnblock(mockGameStatus, mine);
    }


    @Test
    public void testSetGameStatusBLOCKED() {
        Mine mine = new Mine();
        mine.addMineStatusChangedListener(mockListener);

        mine.setGameStatus(BLOCKED, mockGameStatus);
        assertEquals(BLOCKED, mine.getStatus());
        verify(mockListener, never()).onOpen(mockGameStatus, mine);
        verify(mockListener, times(1)).onBlock(mockGameStatus, mine);
        verify(mockListener, never()).onUnblock(mockGameStatus, mine);
    }


}