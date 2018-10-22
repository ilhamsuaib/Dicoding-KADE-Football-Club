package id.ilhamsuaib.footballclub.ui.home.match

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

    fun getMatch(matchType: String?, leagueId: String = HomeActivity.LEAGUE_ID) {
        disposable.add(
                repo.getMatch(matchType, leagueId)
                        .subscribeOn(backgroundScheduler)
                        .observeOn(mainScheduler)
                        .subscribe({
                            callback?.showMatch(matchList = it)
                        }, Throwable::printStackTrace)
        )
    }
}