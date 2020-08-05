package com.github.amazingweather.presentation.ui.activity


import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.github.amazingweather.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class FragmentContainerActivityTest {
    private lateinit var mIdlingResource: IdlingResource

    @Rule
    @JvmField
    var mGrantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        "android.permission.ACCESS_FINE_LOCATION"
    )

    @Before
    fun registerIdlingResource() {
        val activityScenario: ActivityScenario<FragmentContainerActivity> =
            ActivityScenario.launch(FragmentContainerActivity::class.java)
        activityScenario.onActivity { activity ->
            mIdlingResource = activity.testIdlingResource
            // To prove that the test fails, omit this call:
            IdlingRegistry.getInstance().register(mIdlingResource)
        }
    }

    @Test
    fun fragmentContainerActivityTest() {
        onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click())

        onView(withContentDescription("My Location")).check(matches(isDisplayed())).perform(click())

        onView(withId(R.id.mapsView)).check(matches(isDisplayed())).perform(click())

        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(mIdlingResource)
    }

}
