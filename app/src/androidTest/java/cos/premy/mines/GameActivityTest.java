package cos.premy.mines;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.hamcrest.CoreMatchers.allOf;


import cos.premy.mines.graphics.GameActivity;
import cos.premy.mines.graphics.MinesView;
import cos.premy.mines.graphics.SwitchButton;

@RunWith(AndroidJUnit4.class)
public class GameActivityTest {

    private static final int LAYOUT_ID = R.layout.activity_main;
    private static final int ROOT_VIEW_ID = R.id.mines;

    @Before
    public void setUp() {
        Intents.init();
    }

//    @Rule
//    public ActivityScenarioRule<MainActivity> activityRule =
//            new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

//    @Rule
//    public ActivityTestRule<GameActivity> activityTestRule = new ActivityTestRule<>(GameActivity.class);

    @Test
    public void ensureButtonLabels() {
        onView(withId(R.id.start5x5)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.start8x8)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.start10x10)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.start12x12)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.start15x15)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.howToPlay)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.options)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
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
