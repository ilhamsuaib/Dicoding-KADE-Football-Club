package id.ilhamsuaib.footballclub.home

import id.ilhamsuaib.footballclub.data.model.MatchModel

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

interface ServiceCallback {
    fun showMatch(matchList: List<MatchModel>)
    fun onFailed(message: String)
}