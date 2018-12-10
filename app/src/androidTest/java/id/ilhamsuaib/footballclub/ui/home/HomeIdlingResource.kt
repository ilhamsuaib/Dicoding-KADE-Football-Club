package id.ilhamsuaib.footballclub.ui.home

import android.support.test.espresso.IdlingResource

/**
 * Created by @ilhamsuaib on 11/12/18.
 * github.com/ilhamsuaib
 */

class HomeIdlingResource : IdlingResource {

    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = HomeIdlingResource::class.java.simpleName

    override fun isIdleNow(): Boolean {
        val idle = HomeActivity.idlingResourceCounter == 0

        if (idle)
            callback?.onTransitionToIdle()

        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }
}