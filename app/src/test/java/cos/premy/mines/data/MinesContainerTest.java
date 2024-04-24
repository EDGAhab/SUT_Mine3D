package cos.premy.mines.data;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Before;

import cos.premy.mines.GameStatus;
import cos.premy.mines.MyHappyException;
import cos.premy.mines.generator.RandomMinesGenerator;


public class MinesContainerTest {

    private MinesContainer minesContainer;
    private Mine mine;
    private Mine mine1;

    @Mock
    private GameStatus mockGameStatus;





    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        minesContainer = new MinesContainer(5, 5, 7, 7, 0, 0);

    }

//    Failure: java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
//    When the mines number greater than the container cells, it should throw IllegalArgumentException.
//    But, it does not throw any exceptions.

//    Fault: There should be a check in the constructor method to make sure the number of mines
//    will not exceed the the number of container’s cells.

//    @Test(expected = IllegalArgumentException.class)
//    public void testConstructor() {
//        minesContainer = new MinesContainer(2,2,12);
//    }



    @Test
    public void testConstructorAndGetters() {

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

    @Test
    public void testOnOpenRealMineEndsGame() throws MyHappyException {

        mine = minesContainer.getMine(0, 0, 0);
        mine.setIsReal(true);
        int preMineOpened = minesContainer.getMinesOpened();
        mine.setGameStatus(MineStatus.OPENED, mockGameStatus);

        assertTrue(mine.getIsReal());
        verify(mockGameStatus, times(1)).endGame(false);
        assertEquals(preMineOpened, minesContainer.getMinesOpened());

    }

    @Test
    public void testOnOpenNotRealMine() throws MyHappyException {

        mine = minesContainer.getMine(0, 0, 0);
        mine.setIsReal(false);
        int preMineOpened = minesContainer.getMinesOpened();
        mine.setGameStatus(MineStatus.OPENED, mockGameStatus);

        assertFalse(mine.getIsReal());
        verify(mockGameStatus, never()).endGame(false);
        assertEquals(1, minesContainer.getMinesOpened()-preMineOpened);

    }

    @Test
    public void testOnUnblockRealMine() throws MyHappyException {

        mine = minesContainer.getMine(0, 0, 0);
        mine.setIsReal(true);
        int preMineOkBlocked = minesContainer.getOkBlockedMines();
        int preMineBlocked = minesContainer.getMinesBlocked();
        mine.setGameStatus(MineStatus.UNBLOCKED, mockGameStatus);

        assertTrue(mine.getIsReal());
        verify(mockGameStatus, never()).endGame(false);
        assertEquals(1, preMineOkBlocked - minesContainer.getOkBlockedMines());
        assertEquals(1, preMineBlocked - minesContainer.getMinesBlocked());


    }

    @Test
    public void testOnBlockRealMine() throws MyHappyException {

        mine = minesContainer.getMine(0, 0, 0);
        mine.setIsReal(true);
        int preMineOkBlocked = minesContainer.getOkBlockedMines();
        int preMineBlocked = minesContainer.getMinesBlocked();
        mine.setGameStatus(MineStatus.BLOCKED, mockGameStatus);

        assertTrue(mine.getIsReal());
        verify(mockGameStatus, never()).endGame(false);
        assertEquals(-1, preMineOkBlocked - minesContainer.getOkBlockedMines());
        assertEquals(-1, preMineBlocked - minesContainer.getMinesBlocked());


    }

    @Test(expected = MyHappyException.class)
    public void testOnUnblockRealMineEndGameButFactorized() throws MyHappyException {
        RandomMinesGenerator randomMinesGenerator = new RandomMinesGenerator();
        minesContainer = randomMinesGenerator.getNewProblem(1,1,1);
        mine = minesContainer.getMine(0, 0, 0);
        mine.setIsReal(true);
        int preMineOkBlocked = minesContainer.getOkBlockedMines();
        mine.setGameStatus(MineStatus.UNBLOCKED, mockGameStatus);

        assertTrue(mine.getIsReal());
        verify(mockGameStatus, times(1)).endGame(false);
        assertEquals(1, preMineOkBlocked - minesContainer.getOkBlockedMines());

    }

//    failure: java.lang.AssertionError: expected:<0> but was:<-1>
//    fault: When unblocked a mine, it should check if the mine is blocked.
//           If the mine is not blocked, it cannot be unblocked.

//    @Test
//    public void testOnUnBlockRealMine() throws MyHappyException {
//        minesContainer = new MinesContainer(1,1,1);
//        mine = minesContainer.getMine(0, 0, 0);
//        mine.setIsReal(true);
//        mine.setGameStatus(MineStatus.UNBLOCKED, mockGameStatus);
//
//        assertTrue(mine.getIsReal());
//        assertEquals(0, minesContainer.getOkBlockedMines());
//        assertEquals(0, minesContainer.getMinesBlocked());
//
//
//    }

    @Test
    public void testOnUnblockBlockRealMineEndGame() throws MyHappyException {
        minesContainer = new MinesContainer(1,1,1);
        mine1 = minesContainer.getMine(1, 0, 0);
        mine1.setIsReal(false);
        mine = minesContainer.getMine(0,0,0);
        mine.setIsReal(true);
        int preMineOkBlocked = minesContainer.getOkBlockedMines();
        int preMineBlocked = minesContainer.getMinesBlocked();
        mine1.setGameStatus(MineStatus.BLOCKED, mockGameStatus);
        mine1.setGameStatus(MineStatus.UNBLOCKED, mockGameStatus);
        mine.setGameStatus(MineStatus.BLOCKED, mockGameStatus);

        assertFalse(mine1.getIsReal());
        assertTrue(mine.getIsReal());
        assertEquals(minesContainer.getMinesBlocked(), minesContainer.getOkBlockedMines());
        assertEquals(minesContainer.getOkBlockedMines(), minesContainer.getMinesNumber());
        verify(mockGameStatus, times(1)).endGame(true);
        assertEquals(-1, preMineOkBlocked - minesContainer.getOkBlockedMines());
        assertEquals(-1, preMineBlocked - minesContainer.getMinesBlocked());

    }

    @Test
    public void testOnUnblockBlockOkBlockedNotEqualBlocked() throws MyHappyException {
        minesContainer = new MinesContainer(1,1,1);
        mine1 = minesContainer.getMine(1, 0, 0);
        mine1.setIsReal(false);
        mine = minesContainer.getMine(0,0,0);
        mine.setIsReal(true);
        int preMineOkBlocked = minesContainer.getOkBlockedMines();
        int preMineBlocked = minesContainer.getMinesBlocked();
        mine1.setGameStatus(MineStatus.BLOCKED, mockGameStatus);
        mine.setGameStatus(MineStatus.BLOCKED, mockGameStatus);


        assertFalse(mine1.getIsReal());
        assertTrue(mine.getIsReal());
        assertNotEquals(minesContainer.getMinesBlocked(), minesContainer.getOkBlockedMines());
        assertEquals(minesContainer.getOkBlockedMines(), minesContainer.getMinesNumber());
        verify(mockGameStatus, never()).endGame(true);
        assertEquals(1, minesContainer.getOkBlockedMines());
        assertEquals(2,  minesContainer.getMinesBlocked());

    }

    @Test
    public void testOnUnblockBlockOkBlockedNotEqualMineNumber() throws MyHappyException {
        minesContainer = new MinesContainer(1,1,2);
        mine1 = minesContainer.getMine(1, 0, 0);
        mine1.setIsReal(true);
        mine = minesContainer.getMine(0,0,0);
        mine.setIsReal(true);
        int preMineOkBlocked = minesContainer.getOkBlockedMines();
        int preMineBlocked = minesContainer.getMinesBlocked();
        mine.setGameStatus(MineStatus.BLOCKED, mockGameStatus);


        assertTrue(mine1.getIsReal());
        assertTrue(mine.getIsReal());
        assertEquals(minesContainer.getMinesBlocked(), minesContainer.getOkBlockedMines());
        assertNotEquals(minesContainer.getOkBlockedMines(), minesContainer.getMinesNumber());
        verify(mockGameStatus, never()).endGame(true);
        assertEquals(1, minesContainer.getOkBlockedMines());
        assertEquals(1,  minesContainer.getMinesBlocked());
        assertEquals(2, minesContainer.getMinesNumber());

    }

    @Test
    public void testOnUnblockOkBlockedNotEqualMineNumber() throws MyHappyException {
        minesContainer = new MinesContainer(1,1,2);
        mine1 = minesContainer.getMine(1, 0, 0);
        mine1.setIsReal(true);
        mine = minesContainer.getMine(0,0,0);
        mine.setIsReal(true);

        mine1.setGameStatus(MineStatus.BLOCKED, mockGameStatus);
        mine1.setGameStatus(MineStatus.UNBLOCKED, mockGameStatus);

        assertTrue(mine1.getIsReal());
        assertTrue(mine.getIsReal());
        assertEquals(minesContainer.getMinesBlocked(), minesContainer.getOkBlockedMines());
        assertNotEquals(minesContainer.getOkBlockedMines(), minesContainer.getMinesNumber());
        verify(mockGameStatus, never()).endGame(true);
        assertEquals(0, minesContainer.getOkBlockedMines());
        assertEquals(0,  minesContainer.getMinesBlocked());
        assertEquals(2, minesContainer.getMinesNumber());

    }

    @Test
    public void testOnUnblockOkBlockedNotEqualBlocked() throws MyHappyException {
        minesContainer = new MinesContainer(1,1,2);
        mine1 = minesContainer.getMine(1, 0, 0);
        mine1.setIsReal(false);
        mine = minesContainer.getMine(0,0,0);
        mine.setIsReal(true);
        mine1.setGameStatus(MineStatus.BLOCKED, mockGameStatus);
        mine1.setGameStatus(MineStatus.UNBLOCKED, mockGameStatus);


        assertFalse(mine1.getIsReal());
        assertTrue(mine.getIsReal());
        assertEquals(minesContainer.getMinesBlocked(), minesContainer.getOkBlockedMines());
        assertNotEquals(minesContainer.getOkBlockedMines(), minesContainer.getMinesNumber());
        verify(mockGameStatus, never()).endGame(true);
        assertEquals(0, minesContainer.getOkBlockedMines());
        assertEquals(0,  minesContainer.getMinesBlocked());
        assertEquals(2, minesContainer.getMinesNumber());

    }

    @Test
    public void testOnUnblockEndGame() throws MyHappyException {
        minesContainer = new MinesContainer(1,1,1);
        mine1 = minesContainer.getMine(1, 0, 0);
        mine1.setIsReal(false);
        mine = minesContainer.getMine(0,0,0);
        mine.setIsReal(true);
        mine1.setGameStatus(MineStatus.BLOCKED, mockGameStatus);
        mine.setGameStatus(MineStatus.BLOCKED, mockGameStatus);
        mine1.setGameStatus(MineStatus.UNBLOCKED, mockGameStatus);


        assertFalse(mine1.getIsReal());
        assertTrue(mine.getIsReal());
        assertEquals(minesContainer.getMinesBlocked(), minesContainer.getOkBlockedMines());
        assertEquals(minesContainer.getOkBlockedMines(), minesContainer.getMinesNumber());
        verify(mockGameStatus, times(1)).endGame(true);
        assertEquals(1, minesContainer.getOkBlockedMines());
        assertEquals(1,  minesContainer.getMinesBlocked());
        assertEquals(1, minesContainer.getMinesNumber());

    }



    @Test
    public void testonUnblockNotRealMine() throws MyHappyException {

        mine = minesContainer.getMine(0, 0, 0);
        mine.setIsReal(false);
        int preMineOkBlocked = minesContainer.getOkBlockedMines();
        int preMineBlocked = minesContainer.getMinesBlocked();
        mine.setGameStatus(MineStatus.UNBLOCKED, mockGameStatus);

        assertFalse(mine.getIsReal());
        verify(mockGameStatus, never()).endGame(false);
        assertEquals(0, preMineOkBlocked - minesContainer.getOkBlockedMines());
        assertEquals(1, preMineBlocked-minesContainer.getMinesBlocked());


    }





}
