package cos.premy.mines;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;

@RunWith(AndroidJUnit4.class)
public class GameActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @Test
    public void testbutton5() {
        onView(withId(R.id.start5x5)).perform(click());
        intended(hasComponent(OptionActivity.class.getName()));
    }

    @Test
    public void testbutton8() {
        onView(withId(R.id.start8x8)).perform(click());
        intended(hasComponent(OptionActivity.class.getName()));
    }

    @Test
    public void testbutton10() {
        onView(withId(R.id.start10x10)).perform(click());
        intended(hasComponent(OptionActivity.class.getName()));
    }

    @Test
    public void testbutton12() {
        onView(withId(R.id.start12x12)).perform(click());
        intended(hasComponent(OptionActivity.class.getName()));
    }

    @Test
    public void testbutton15() {
        onView(withId(R.id.start15x15)).perform(click());
        intended(hasComponent(OptionActivity.class.getName()));
    }

    @Test
    public void testOptionsButtonLaunchesOptionActivity() {
        onView(withId(R.id.options)).perform(click());
        intended(hasComponent(OptionActivity.class.getName()));
    }

    @After
    public void tearDown() {
        Intents.release();
    }
}
