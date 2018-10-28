package id.ilhamsuaib.footballclub.ui.teamDetail

import id.ilhamsuaib.footballclub.base.BasePresenter
import id.ilhamsuaib.footballclub.data.Repository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

class TeamPresenter(private val repo: Repository,
                    private val backgroundScheduler: Scheduler = Schedulers.io(),
                    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()) : BasePresenter<ServiceCallback>() {

    fun getTeamPlayers(idTeam: String?) {
        callback?.showProgress(true)
        val obs = repo.getTeamPlayer(idTeam)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe({
                    callback?.showPlayers(it)
                    callback?.showProgress(false)
                }, {
                    it.printStackTrace()
                    callback?.showProgress(false)
                })

        disposable.add(obs)
    }

}