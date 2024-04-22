//package cos.premy.mines;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//
//import android.view.View;
//
//import androidx.test.espresso.ViewAction;
//import androidx.test.espresso.action.CoordinatesProvider;
//import androidx.test.espresso.action.GeneralClickAction;
//import androidx.test.espresso.action.Press;
//import androidx.test.espresso.action.Tap;
//import androidx.test.espresso.matcher.RootMatchers;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.platform.app.InstrumentationRegistry;
//import androidx.test.uiautomator.UiDevice;
//
//import org.hamcrest.Matchers;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//
//
//
//
//@RunWith(MockitoJUnitRunner.class)
//public class MineSweeperGameTest {
//
//
//    public static ViewAction clickXY(final int x, final int y){
//        return new GeneralClickAction(
//                Tap.SINGLE,
//                new CoordinatesProvider() {
//                    @Override
//                    public float[] calculateCoordinates(View view) {
//
//                        final int[] screenPos = new int[2];
//                        view.getLocationOnScreen(screenPos);
//
//                        final float screenX = screenPos[0] + x;
//                        final float screenY = screenPos[1] + y;
//                        float[] coordinates = {screenX, screenY};
//
//                        return coordinates;
//                    }
//                },
//                Press.FINGER);
//    }
//
//    @Rule
//    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
//
//    @Test
//    public void userPlaysAndLoses() throws InterruptedException {
//        onView(withId(R.id.start5x5)).perform(click());
//        int x = 10;
//        int y = 10;
////        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
////        mDevice.swipe(x, y, x, y, 100);
////        Thread.sleep(2000);
//        clickXY(x,y);
//        clickXY(x,y);
//        onView(withText(Matchers.containsString("You have lost.")))
//                .inRoot(RootMatchers.isDialog())
//                .check(matches(isDisplayed()));
//    }
//}
