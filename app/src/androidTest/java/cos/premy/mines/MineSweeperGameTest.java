package cos.premy.mines;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MineSweeperGameTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void userPlaysAndLoses() throws InterruptedException {
        onView(withId(R.id.start5x5)).perform(click());
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        int x = 100;
        int y = 200;
        mDevice.swipe(x, y, x, y, 100);
        Thread.sleep(2000);
        onView(withText(Matchers.containsString("You have lost.")))
                .inRoot(RootMatchers.isDialog())
                .check(matches(isDisplayed()));
    }
}
