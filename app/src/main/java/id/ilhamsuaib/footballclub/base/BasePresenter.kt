package id.ilhamsuaib.footballclub.base

import id.ilhamsuaib.footballclub.data.Repository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by @ilhamsuaib on 06/10/18.
 * github.com/ilhamsuaib
 */

open class BasePresenter<T : BaseServiceCallback> {

    protected var callback: T? = null
    protected val disposable = CompositeDisposable()
    /*protected var backgroundSchedulers: Schedulers = Schedulers.*/

    fun bindCallback(callback: T?) {
        this.callback = callback
    }

    fun unbind() {
        this.callback = null
        if (!disposable.isDisposed)
            disposable.dispose()
    }
}