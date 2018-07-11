package com.tech.transaction

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class InputFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, true)

    @Test
    fun transactionInputFragmentTest() {
        val appCompatEditText = onView(
                allOf(withId(R.id.etAmount),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainer),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText.perform(click())


        val appCompatEditText2 = onView(
                allOf(withId(R.id.etAmount),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainer),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText2.perform(replaceText("3"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
                allOf(withId(R.id.etAmount), withText("3"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainer),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText3.perform(click())

        val appCompatEditText4 = onView(
                allOf(withId(R.id.etAmount), withText("3"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainer),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText4.perform(replaceText("3223"))

        val appCompatEditText5 = onView(
                allOf(withId(R.id.etAmount), withText("3223"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainer),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText5.perform(closeSoftKeyboard())

        val appCompatEditText6 = onView(
                allOf(withId(R.id.etAmount), withText("3223"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainer),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText6.perform(pressImeActionButton())

        val appCompatButton3 = onView(
                allOf(withId(R.id.btnSubmit), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainer),
                                        0),
                                0),
                        isDisplayed()))
        appCompatButton3.perform(click())

    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
