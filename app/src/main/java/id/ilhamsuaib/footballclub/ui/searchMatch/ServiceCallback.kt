package id.ilhamsuaib.footballclub.ui.searchMatch

import id.ilhamsuaib.footballclub.base.BaseServiceCallback
import id.ilhamsuaib.footballclub.model.Match

/**
 * Created by @ilhamsuaib on 30/10/18.
 * github.com/ilhamsuaib
 */

interface ServiceCallback : BaseServiceCallback {
    fun showProgress(show: Boolean)
    fun showMatchResults(matchList: List<Match>)
}