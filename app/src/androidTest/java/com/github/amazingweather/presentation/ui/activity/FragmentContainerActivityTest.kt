package com.github.amazingweather.presentation.ui.activity


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.github.amazingweather.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FragmentContainerActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(FragmentContainerActivity::class.java)

    @Test
    fun fragmentContainerActivityTest() {
        val cardView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerView),
                        childAtPosition(
                            withId(R.id.swipeRefreshLayout),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cardView.check(matches(isDisplayed()))

        val recyclerView = onView(
            allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    withId(R.id.swipeRefreshLayout),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val cardView2 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerView),
                        childAtPosition(
                            withId(R.id.swipeRefreshLayout),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cardView2.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

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
