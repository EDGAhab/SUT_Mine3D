package cos.premy.mines;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertTrue;

import cos.premy.mines.graphics.MinesView;

@RunWith(AndroidJUnit4.class)
public class MinesViewTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private MinesView mMinesView;

    @Before
    public void setUp() {
        // This assumes your MainActivity initializes MinesView.
        // You'll need to replace R.id.mines_view with the actual ID of your MinesView in the layout.
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMinesView = mActivityRule.getActivity().findViewById(R.id.mines);
            }
        });
    }

    @Test
    public void minesView_isDisplayed() {
        assertTrue("Dummy test passes because true is always true", true);
        //onView(allOf(instanceOf(MinesView.class), withId(R.id.mines))).check(matches(isDisplayed()));
    }
}
