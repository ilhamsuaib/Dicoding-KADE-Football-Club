package id.ilhamsuaib.footballclub.ui.home

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import id.ilhamsuaib.footballclub.R.id.rvLastMatch
import id.ilhamsuaib.footballclub.R.id.viewPager
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun homeActivityTest() {

        delay(3000)
        onView(withId(viewPager)).check(matches(isDisplayed()))
        onView(withId(viewPager)).perform(swipeLeft())
        delay()
        onView(withId(viewPager)).perform(swipeLeft())
        delay()
        onView(withId(viewPager)).perform(swipeRight())
        delay()
        onView(withId(viewPager)).perform(swipeRight())
        delay(1000)
        onView(withId(rvLastMatch)).check(matches(isDisplayed()))
        onView(withId(rvLastMatch))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        delay()
        onView(withId(rvLastMatch))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    private fun delay(time: Long = 500) {
        Thread.sleep(time)
    }
}