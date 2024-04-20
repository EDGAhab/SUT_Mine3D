package cos.premy.mines.data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

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
    public void testSetIsRealWhenNotFactorized() throws MyHappyException {
        mine.setIsReal(false);
        assertFalse(mine.getIsReal());
    }
//
//    @Test(expected = MyHappyException.class)
//    public void testSetIsRealWhenFactorizedThrowsException() {
//        mine.setFactorized();
//        mine.setIsReal(false); // This should throw the MyHappyException
//    }

//    @Test(expected = MyHappyException.class)
//    public void testSetStatusWhenFactorizedThrowsException() {
//        mine.setFactorized();
//        mine.setStatus(MineStatus.BLOCKED); // This should throw the MyHappyException
//    }

    @Test
    public void testSetGameStatusNotifiesListenerOnUnblock() {
        mine.setGameStatus(MineStatus.UNBLOCKED, mockGameStatus);
        verify(mockListener).onUnblock(mockGameStatus, mine);
    }

    @Test
    public void testSetGameStatusNotifiesListenerOnOpen() {
        mine.setGameStatus(MineStatus.OPENED, mockGameStatus);
        verify(mockListener).onOpen(mockGameStatus, mine);
    }

    @Test
    public void testSetGameStatusNotifiesListenerOnBlock() {
        mine.setGameStatus(MineStatus.BLOCKED, mockGameStatus);
        verify(mockListener).onBlock(mockGameStatus, mine);
    }
}
