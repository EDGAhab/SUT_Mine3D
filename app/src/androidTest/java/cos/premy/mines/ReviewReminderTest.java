package cos.premy.mines;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReviewReminderTest {

    @Mock
    Context mockContext;
    @Mock
    Activity mockActivity;
    @Mock
    SharedPreferences mockSharedPreferences;
    @Mock
    SharedPreferences.Editor mockEditor;
    @Mock
    Dialog mockDialog;

    @Before
    public void setUp() {
        when(mockActivity.getSharedPreferences(anyString(), anyInt())).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        when(mockEditor.putBoolean(anyString(), anyBoolean())).thenReturn(mockEditor);
    }

//    @Test
//    public void reviewReminder_ShowsDialog_WhenReadyForReview() {
//        when(mockSharedPreferences.getBoolean("ready", false)).thenReturn(true);
//        when(mockSharedPreferences.getBoolean("reviewed", false)).thenReturn(false);
//        ReviewReminder.startPotentialReviewReminding(mockActivity);
//        verify(mockDialog).show();
//    }

    @Test
    public void reviewReminder_DoesNotShowDialog_WhenAlreadyReviewed() {
        when(mockSharedPreferences.getBoolean("ready", false)).thenReturn(true);
        when(mockSharedPreferences.getBoolean("reviewed", false)).thenReturn(true);
        ReviewReminder.startPotentialReviewReminding(mockActivity);
    }

//    @Test
//    public void reviewReminder_SetsReviewed_WhenUserChoosesToRate() {
//        mockEditor.commit();
//        verify(mockEditor).putBoolean("reviewed", true);
//        verify(mockEditor).commit();
//    }
}

