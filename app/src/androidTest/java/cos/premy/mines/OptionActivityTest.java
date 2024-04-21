package cos.premy.mines;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class OptionActivityTest {

    @Rule
    public ActivityTestRule<OptionActivity> activityRule = new ActivityTestRule<>(OptionActivity.class, true, false);

    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        sharedPreferences = context.getSharedPreferences("cos.premy.mines.settings", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("hardcore", false);
        editor.putBoolean("colored", false);
        editor.putBoolean("numberType", false);
        editor.putBoolean("flood", false);
        editor.apply();

        activityRule.launchActivity(null);
    }

    @Test
    public void toggleButtons_UpdateSharedPreferences() {
        onView(withId(R.id.mode)).check(matches(isNotChecked()));
        onView(withId(R.id.color)).check(matches(isNotChecked()));
        onView(withId(R.id.numberType)).check(matches(isNotChecked()));
        onView(withId(R.id.flood)).check(matches(isNotChecked()));

        onView(withId(R.id.mode)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.color)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.numberType)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.flood)).perform(click()).check(matches(isChecked()));

        boolean isHardcore = sharedPreferences.getBoolean("hardcore", false);
        boolean isColored = sharedPreferences.getBoolean("colored", false);
        boolean isNumberType = sharedPreferences.getBoolean("numberType", false);
        boolean isFlood = sharedPreferences.getBoolean("flood", false);

        assert(isHardcore);
        assert(isColored);
        assert(isNumberType);
        assert(isFlood);
    }

    @Test
    public void double_click() {
        onView(withId(R.id.mode)).check(matches(isNotChecked()));
        onView(withId(R.id.color)).check(matches(isNotChecked()));
        onView(withId(R.id.numberType)).check(matches(isNotChecked()));
        onView(withId(R.id.flood)).check(matches(isNotChecked()));

        onView(withId(R.id.mode)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.color)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.numberType)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.flood)).perform(click()).check(matches(isChecked()));

        onView(withId(R.id.mode)).perform(click()).check(matches(isNotChecked()));
        onView(withId(R.id.color)).perform(click()).check(matches(isNotChecked()));
        onView(withId(R.id.numberType)).perform(click()).check(matches(isNotChecked()));
        onView(withId(R.id.flood)).perform(click()).check(matches(isNotChecked()));

        boolean isHardcore = sharedPreferences.getBoolean("hardcore", true);
        boolean isColored = sharedPreferences.getBoolean("colored", true);
        boolean isNumberType = sharedPreferences.getBoolean("numberType", true);
        boolean isFlood = sharedPreferences.getBoolean("flood", true);

        assertFalse(isHardcore);
        assertFalse(isColored);
        assertFalse(isNumberType);
        assertFalse(isFlood);
    }
}
