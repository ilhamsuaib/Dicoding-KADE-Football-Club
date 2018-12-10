package id.ilhamsuaib.footballclub.ui.home.matches

import id.ilhamsuaib.footballclub.base.BasePresenter
import id.ilhamsuaib.footballclub.data.Repository
import id.ilhamsuaib.footballclub.ui.home.HomeActivity
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

class MatchPresenter(private val repo: Repository,
                     private val backgroundScheduler: Scheduler = Schedulers.io(),
                     private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()) : BasePresenter<ServiceCallback>() {

    fun getMatch(matchType: String?, leagueId: String) {
        callback?.showProgress(true)
        val obs = repo.getMatch(matchType, leagueId)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe({
                    callback?.showProgress(false)
                    callback?.showMatch(matchList = it)
                    HomeActivity.idlingResourceCounter = 0
                }, {
                    it.printStackTrace()
                    callback?.showProgress(false)
                })

        disposable.add(obs)
    }
}