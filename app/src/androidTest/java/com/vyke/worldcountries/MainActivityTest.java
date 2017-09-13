package com.vyke.worldcountries;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.vyke.worldcountries.MainActivity;

import com.android.vyke.worldcountries.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction textView = onView(
                allOf(ViewMatchers.withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView.perform(replaceText("Languages: Albanian  "), closeSoftKeyboard());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView2.perform(replaceText("Currency: Albanian lek L,  "), closeSoftKeyboard());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView3.perform(replaceText("Languages: Pashto  Uzbek  Turkmen  "), closeSoftKeyboard());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView4.perform(replaceText("Currency: Afghan afghani ؋,  "), closeSoftKeyboard());

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView5.perform(replaceText("Languages: Albanian  "), closeSoftKeyboard());

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView6.perform(replaceText("Currency: Albanian lek L,  "), closeSoftKeyboard());

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView7.perform(replaceText("Languages: Arabic  "), closeSoftKeyboard());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView8.perform(replaceText("Currency: Algerian dinar د.ج,  "), closeSoftKeyboard());

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView9.perform(replaceText("Languages: Pashto  Uzbek  Turkmen  "), closeSoftKeyboard());

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView10.perform(replaceText("Currency: Afghan afghani ؋,  "), closeSoftKeyboard());

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView11.perform(replaceText("Languages: Swedish  "), closeSoftKeyboard());

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView12.perform(replaceText("Currency: Euro €,  "), closeSoftKeyboard());

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView13.perform(replaceText("Languages: Pashto  Uzbek  Turkmen  "), closeSoftKeyboard());

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView14.perform(replaceText("Currency: Afghan afghani ؋,  "), closeSoftKeyboard());

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView15.perform(replaceText("Languages: Swedish  "), closeSoftKeyboard());

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView16.perform(replaceText("Currency: Euro €,  "), closeSoftKeyboard());

        ViewInteraction textView17 = onView(
                allOf(withId(R.id.day), withText("Monday"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView17.check(matches(withText("Monday")));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView18 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView18.perform(replaceText("Languages: Pashto  Uzbek  Turkmen  "), closeSoftKeyboard());

        ViewInteraction textView19 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView19.perform(replaceText("Currency: Afghan afghani ؋,  "), closeSoftKeyboard());

        ViewInteraction textView20 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView20.perform(replaceText("Languages: Swedish  "), closeSoftKeyboard());

        ViewInteraction textView21 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView21.perform(replaceText("Currency: Euro €,  "), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView22 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView22.perform(replaceText("Languages: Pashto  Uzbek  Turkmen  "), closeSoftKeyboard());

        ViewInteraction textView23 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView23.perform(replaceText("Currency: Afghan afghani ؋,  "), closeSoftKeyboard());

        ViewInteraction textView24 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView24.perform(replaceText("Languages: Swedish  "), closeSoftKeyboard());

        ViewInteraction textView25 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView25.perform(replaceText("Currency: Euro €,  "), closeSoftKeyboard());

        ViewInteraction textView26 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView26.perform(replaceText("Languages: Pashto  Uzbek  Turkmen  "), closeSoftKeyboard());

        ViewInteraction textView27 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView27.perform(replaceText("Currency: Afghan afghani ؋,  "), closeSoftKeyboard());

        ViewInteraction textView28 = onView(
                allOf(withId(R.id.langspoke), withText("Languages: "), isDisplayed()));
        textView28.perform(replaceText("Languages: Swedish  "), closeSoftKeyboard());

        ViewInteraction textView29 = onView(
                allOf(withId(R.id.currencies), withText("Currency: "), isDisplayed()));
        textView29.perform(replaceText("Currency: Euro €,  "), closeSoftKeyboard());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
