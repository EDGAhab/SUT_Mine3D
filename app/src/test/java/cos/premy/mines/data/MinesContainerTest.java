package cos.premy.mines.data;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Before;


public class MinesContainerTest {

    @Mock
    Mine mockMine;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConstructorAndGetters() {
        MinesContainer minesContainer = new MinesContainer(5, 5, 7, 7, 0, 0);

        assertEquals(5, minesContainer.getHeight());
        assertEquals(5, minesContainer.getWidth());
        assertEquals(7, minesContainer.getMinesNumber());
        assertEquals(7, minesContainer.getMinesBlocked());
        assertEquals(0, minesContainer.getOkBlockedMines());
        assertEquals(0, minesContainer.getMinesOpened());
        assertNotNull(minesContainer.getMine(0, 0, 0));
    }

    @Test
    public void testIsRealMine() {
        MinesContainer minesContainer = new MinesContainer(5, 5, 7);
        boolean real = minesContainer.isRealMine(0, 0, 0);

        assertFalse(real);
    }

//
//    @Test
//    public void testInitListenersOpeningRealMine() {
//        MinesContainer minesContainer = new MinesContainer(5, 5, 7);
//        verify(minesContainer, times(1)).i

//        minesContainer.getMine(0, 0, 0).setGameStatus(MineStatus.OPENED, gameStatus);
//        verify(gameStatus, times(1)).endGame(false);
//    }
//
//    @Test
//    public void testSetMineNeighbors() {
//        MinesContainer minesContainer = new MinesContainer(3, 3, 3);
//        minesContainer.setMineNeighbors(0, 0, 0);  // Test setting neighbors for a corner mine
//        Mine testMine = minesContainer.getMine(0, 0, 0);
//
//        assertNotNull(testMine.getAllNeighborCoords());
//        assertEquals(3, testMine.getAllNeighborCoords().size());  // A corner mine should have 3 neighbors
//    }
//
//    @Test
//    public void testSetFactorized() {
//        MinesContainer minesContainer = new MinesContainer(5, 5, 7, 7, 0, 0);
//        minesContainer.setFactorized();
//
//        assertTrue(minesContainer.getMine(0, 0, 0).isFactorized());
//    }

}
