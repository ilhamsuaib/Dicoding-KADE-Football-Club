package id.ilhamsuaib.footballclub.ui.home

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import id.ilhamsuaib.footballclub.R.id.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers
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

        /**skenario
         * 1. Menampilkan halaman home
         * 2. Memastikan bottomNavigationReady
         * 3. Click Teams nav
         * 4. Click Favorites nav
         * 5. Click Favorites nav
         * 6. Mastikan view pager ready
         * 7. Swipe ke tab teams
         * 8. Swipe ke tab match
         * 9. Click Matchs nav
         * 10. Pastikan spinner ready
         * 11. Pastikan view pager ready
         * 12. Swipe ke tab last
         * 13. Swipe ke tab next
         * 14. Pastikan recycler view ready
         * 15. Scroll ke item 10
         * 16. Click item ke 10
         * */

        delay(3000)
        onView(withId(bottomNav)).check(matches(isDisplayed()))
        delay()
        onView(withId(menu_teams)).perform(click())
        delay()
        onView(withId(menu_favorite)).perform(click())
        delay()
        onView(withId(viewPagerFavorites)).check(matches(isDisplayed()))
        delay()
        onView(withId(viewPagerFavorites)).perform(swipeLeft())
        delay()
        onView(withId(viewPagerFavorites)).perform(swipeRight())
        delay()
        onView(withId(menu_matches)).perform(click())
        delay()
        onView(withId(spLeagueNextMatch)).check(matches(isDisplayed()))
        delay()
        onView(withId(homeViewPager)).check(matches(isDisplayed()))
        delay()
        onView(withId(homeViewPager)).perform(swipeLeft())
        delay()
        onView(withId(homeViewPager)).perform(swipeRight())
        delay()
        onView(withId(rvNextMatch)).check(matches(isDisplayed()))
        onView(withId(rvNextMatch))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(12))
        delay()
        onView(withId(rvNextMatch))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        delay()
        onView(withId(rvNextMatch))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
    }

    private fun delay(time: Long = 500) {
        Thread.sleep(time)
    }
}