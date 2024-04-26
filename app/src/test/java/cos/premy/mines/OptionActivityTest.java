package cos.premy.mines;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyBoolean;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoadedGame.class})
public class OptionActivityTest {

    @Mock
    private Context mockContext;
    @Mock
    private SharedPreferences mockSharedPreferences;
    @Mock
    private SharedPreferences.Editor mockEditor;
    @Mock
    private Bundle mockBundle;

    private OptionActivity optionActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoadedGame.class);
        when(LoadedGame.mainActivity).thenReturn((Activity) mockContext);
        when(mockContext.getSharedPreferences("cos.premy.mines.settings", Context.MODE_PRIVATE)).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        when(mockEditor.putBoolean(anyString(), anyBoolean())).thenReturn(mockEditor);

        optionActivity = new OptionActivity();
        optionActivity.onCreate(mockBundle);
    }


    @Test
    public void testHardcoreToggle() {
        when(mockSharedPreferences.getBoolean("hardcore", false)).thenReturn(false);
        optionActivity.hardcore.performClick();
        verify(mockEditor).putBoolean("hardcore", true);
        verify(mockEditor).commit();
    }

    @Test
    public void testColorToggle() {
        when(mockSharedPreferences.getBoolean("colored", false)).thenReturn(false);
        optionActivity.color.performClick();
        verify(mockEditor).putBoolean("colored", true);
        verify(mockEditor).commit();
    }


}
