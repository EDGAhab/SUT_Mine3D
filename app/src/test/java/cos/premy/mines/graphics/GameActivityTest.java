package cos.premy.mines.graphics;

import android.content.Intent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import cos.premy.mines.GameStatus;
import cos.premy.mines.R;

@RunWith(RobolectricTestRunner.class)
public class GameActivityTest {
    private GameActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(GameActivity.class);
    }

    @Test
    public void activityShouldNotBeNull() {
        assertNotNull("GameActivity should not be null", activity);
    }

//    @Test
//    public void minesViewShouldBeProperlyInitialized() {
////        MinesView minesView = activity.findViewById(R.id.mines_view); // Replace with actual ID from your layout
////        assertNotNull("MinesView should not be null", minesView);
//
//        // You can also check if MinesView has a non-null GameStatus if that's a requirement
////        GameStatus gameStatus = minesView.getGameStatus(); // Assuming you have a getter for GameStatus
////        assertNotNull("GameStatus in MinesView should not be null", gameStatus);
//    }
}
