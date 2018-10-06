package id.ilhamsuaib.footballclub.base

import id.ilhamsuaib.footballclub.data.ApiService

/**
 * Created by @ilhamsuaib on 06/10/18.
 * github.com/ilhamsuaib
 */

open class BasePresenter<T> {

    protected var callback: T? = null
    protected val apiService: ApiService by lazy {
        BaseApp.API_SERVICE
    }

    fun bindCallback(callback: T?) {
        this.callback = callback
    }

    fun unbind() {
        this.callback = null
    }
}