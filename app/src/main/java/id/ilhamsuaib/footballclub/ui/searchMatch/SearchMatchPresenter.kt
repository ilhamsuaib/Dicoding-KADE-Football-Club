package id.ilhamsuaib.footballclub.ui.searchMatch

import id.ilhamsuaib.footballclub.base.BasePresenter
import id.ilhamsuaib.footballclub.data.Repository
import id.ilhamsuaib.footballclub.utilities.logD
import id.ilhamsuaib.footballclub.utilities.toJson
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by @ilhamsuaib on 30/10/18.
 * github.com/ilhamsuaib
 */

class SearchMatchPresenter(private val repo: Repository,
                           private val backgroundScheduler: Scheduler = Schedulers.io(),
                           private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()) : BasePresenter<ServiceCallback>() {

    fun searchMatch(s: String?) {
        if (s.isNullOrBlank()) return
        callback?.showProgress(true)
        val obs = repo.searchMatch(s)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe({
                    logD("tag", "search: Result : ${it.toJson()}")
                    callback?.showMatchResults(it)
                    callback?.showProgress(false)
                }, {
                    it.printStackTrace()
                    callback?.showProgress(false)
                })
        disposable.add(obs)
    }
}