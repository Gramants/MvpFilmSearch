package app.go.search;

        import android.support.test.rule.ActivityTestRule;
        import android.support.test.runner.AndroidJUnit4;
        import android.test.suitebuilder.annotation.LargeTest;

        import org.junit.Rule;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        import app.go.search.view.FilmListActivity;

        import static android.support.test.espresso.Espresso.onView;
        import static android.support.test.espresso.action.ViewActions.click;
        import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
        import static android.support.test.espresso.action.ViewActions.typeText;
        import static android.support.test.espresso.matcher.ViewMatchers.withId;
        import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {

    private static final String STRING_TO_BE_TYPED = "Rocky";

    @Rule
    public ActivityTestRule<FilmListActivity> mActivityRule = new ActivityTestRule<>(
            FilmListActivity.class);

    @Test
    public void sayHello(){
        onView(withId(R.id.edit_text_username)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.button_search)).perform(click());

    }

}