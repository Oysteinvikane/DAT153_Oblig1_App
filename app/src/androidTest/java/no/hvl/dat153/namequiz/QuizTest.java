package no.hvl.dat153.namequiz;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizTest {


    @Rule
    public ActivityTestRule<QuizActivity> activityRule = new ActivityTestRule(QuizActivity.class);

    @Test
    public void scoreIsCorrect() {
        int num = QuizActivity.randNum;
        onView(withId(R.id.score)).check(matches(withSubstring("0")));
        onView(withId(R.id.textInputEditText)).perform(typeText(DatabaseList.ITEMS.get(num).getName()), closeSoftKeyboard());
        onView(withId(R.id.answerButton)).perform(click());
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.score)).check(matches(withSubstring("1")));

    }

    @Test
    public void scoreIsNotCorrect() {
        onView(withId(R.id.score)).check(matches(withSubstring("0")));
        onView(withId(R.id.textInputEditText)).perform(typeText("blablabls"), closeSoftKeyboard());
        onView(withId(R.id.answerButton)).perform(click());
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.score)).check(matches(withSubstring("0")));

    }


}
