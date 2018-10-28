package id.ilhamsuaib.footballclub.ui.home.teams

import id.ilhamsuaib.footballclub.base.BasePresenter
import id.ilhamsuaib.footballclub.data.Repository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

class TeamsPresenter(private val repo: Repository,
                     private val backgroundScheduler: Scheduler = Schedulers.io(),
                     private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()) : BasePresenter<ServiceCallback>() {

    fun getTeams(strLeague: String) {
        callback?.showProgress(true)
        val obs = repo.getTeams(strLeague)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe({
                    callback?.showTeams(it)
                    callback?.showProgress(false)
                }, {
                    it.printStackTrace()
                    callback?.showProgress(false)
                })

        disposable.add(obs)
    }
}