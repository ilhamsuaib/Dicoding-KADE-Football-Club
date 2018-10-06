package id.ilhamsuaib.footballclub.home

import id.ilhamsuaib.footballclub.base.BasePresenter
import id.ilhamsuaib.footballclub.utilities.getResponse

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

class MatchPresenter : BasePresenter<ServiceCallback>() {

    fun getMatch(matchType: String?, leagueId: String = MatchFragment.LEAGUE_ID) {
        apiService.getMatch(matchType, leagueId)
                .getResponse({
                    callback?.onFailed(it)
                }, { response ->
                    response?.events?.let {
                        callback?.showMatch(matchList = it)
                    }
                })
    }
}