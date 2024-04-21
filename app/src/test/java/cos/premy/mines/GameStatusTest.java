package cos.premy.mines;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import android.app.Activity;
import android.content.SharedPreferences;

public class GameStatusTest {
    @Mock
    private Activity mockActivity;
    @Mock
    private SharedPreferences mockSharedPreferences;
    @Mock
    private SharedPreferences.Editor mockEditor;
    @Mock
    private GameEndedListener mockGameEndedListener;
    @Mock
    private LevelSwitchListener mockLevelSwitchListener;

    private GameStatus gameStatus;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockActivity.getSharedPreferences(anyString(), anyInt())).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        when(mockEditor.putBoolean(anyString(), anyBoolean())).thenReturn(mockEditor);
        gameStatus = new GameStatus(mockActivity);
    }

    @Test
    public void testConstructor() {
        assertNotNull(gameStatus.getStartTime());
        assertTrue(gameStatus.stillPlaying());
        assertFalse(gameStatus.hasUserWon());
        assertEquals(0, gameStatus.getLevel());
    }

    @Test
    public void testEndGame() {
        gameStatus.addGameEndedListener(mockGameEndedListener);
        gameStatus.endGame(true);
        assertFalse(gameStatus.stillPlaying());
        assertTrue(gameStatus.hasUserWon());
        assertNotNull(gameStatus.getEndTime());
        verify(mockGameEndedListener).gameEnded(gameStatus);
    }


    @Test
    public void testSwitchLevel() {
        gameStatus.addLevelSwitchListener(mockLevelSwitchListener);
        int initialLevel = gameStatus.getLevel();
        gameStatus.switchLevel();
        assertNotEquals(initialLevel, gameStatus.getLevel());
        verify(mockLevelSwitchListener).levelSwitched(gameStatus);
    }



    @Test
    public void testSharedPreferences() {
        when(mockSharedPreferences.getBoolean("hardcore", false)).thenReturn(true);
        when(mockSharedPreferences.getBoolean("numberType", false)).thenReturn(true);
        when(mockSharedPreferences.getBoolean("colored", false)).thenReturn(true);
        when(mockSharedPreferences.getBoolean("flood", false)).thenReturn(true);

        assertTrue("Hardcore should be true", gameStatus.getHardcore());
        assertTrue("NumberType should be true", gameStatus.getNumberType());
        assertTrue("Colored should be true", gameStatus.getColored());
        assertTrue("Flood should be true", gameStatus.getFlood());
    }

    @Test
    public void testCleanListeners() {
        gameStatus.addLevelSwitchListener(mockLevelSwitchListener);
        gameStatus.cleanListeners();
        gameStatus.switchLevel();
        verifyNoInteractions(mockLevelSwitchListener);
    }
}

