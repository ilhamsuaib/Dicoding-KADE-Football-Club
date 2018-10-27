package id.ilhamsuaib.footballclub.ui.home.matches

import id.ilhamsuaib.footballclub.base.BaseServiceCallback
import id.ilhamsuaib.footballclub.model.Match

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

interface ServiceCallback : BaseServiceCallback {
    fun showMatch(matchList: List<Match>)
    fun showProgress(show: Boolean)
}