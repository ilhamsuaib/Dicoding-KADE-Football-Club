package id.ilhamsuaib.footballclub.base

import id.ilhamsuaib.footballclub.utilities.NetworkConfig
import id.ilhamsuaib.footballclub.data.remote.ApiService

/**
 * Created by @ilhamsuaib on 06/10/18.
 * github.com/ilhamsuaib
 */

open class BasePresenter<T: BaseServiceCallback> {

    protected var callback: T? = null
    protected val apiService: ApiService by lazy {
        NetworkConfig.getApiService()
    }

    fun bindCallback(callback: T?) {
        this.callback = callback
    }

    fun unbind() {
        this.callback = null
    }
}