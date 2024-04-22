package cos.premy.mines;

import android.app.Activity;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameStatusTest {

    @Mock
    private Activity mockActivity;
    @Mock
    private SharedPreferences mockSharedPreferences;
    @Mock
    private SharedPreferences.Editor mockEditor;
    @Mock
    private LevelSwitchListener mockLevelSwitchListener;
    @Mock
    private GameEndedListener mockGameEndedListener;

    private GameStatus gameStatus;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mockActivity.getSharedPreferences("cos.premy.mines.settings", Activity.MODE_PRIVATE)).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        when(mockEditor.putBoolean(anyString(), anyBoolean())).thenReturn(mockEditor);

        gameStatus = new GameStatus(mockActivity);
    }

    @Test
    public void testLevelSwitch() {
        gameStatus.addLevelSwitchListener(mockLevelSwitchListener);
        assertEquals(0, gameStatus.getLevel());

        gameStatus.switchLevel();
        assertEquals(1, gameStatus.getLevel());
        verify(mockLevelSwitchListener).levelSwitched(gameStatus);

        gameStatus.switchLevel();
        assertEquals(0, gameStatus.getLevel());
        verify(mockLevelSwitchListener, times(2)).levelSwitched(gameStatus);
    }

    @Test
    public void testSharedPreferences() {
        when(mockSharedPreferences.getBoolean("hardcore", false)).thenReturn(true);
        when(mockSharedPreferences.getBoolean("numberType", false)).thenReturn(true);
        when(mockSharedPreferences.getBoolean("colored", false)).thenReturn(true);
        when(mockSharedPreferences.getBoolean("flood", false)).thenReturn(true);

        assertTrue(gameStatus.getHardcore());
        assertTrue(gameStatus.getNumberType());
        assertTrue(gameStatus.getColored());
        assertTrue(gameStatus.getFlood());
    }
}

