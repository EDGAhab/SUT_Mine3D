package cos.premy.mines;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testButton1() {
        // Replace R.id.button1 with the actual ID of the button
        onView(withId(R.id.start5x5)).perform(click());
        // Verify that the expected UI change occurs after the button click
        // For example, if clicking button1 opens a new Fragment, check if that Fragment is displayed
    }

    @Test
    public void testButton2() {
        // Replace R.id.button2 with the actual ID of the button
        onView(withId(R.id.start8x8)).perform(click());
        // Add assertions here to verify the result of clicking button2
    }

    // Add more test methods for other buttons in MainActivity
}
