package id.ilhamsuaib.footballclub.home

import id.ilhamsuaib.footballclub.BaseApp
import id.ilhamsuaib.footballclub.data.ApiService
import id.ilhamsuaib.footballclub.getResponse

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

class MatchPresenter(private val view: MatchView) {

    private val apiService: ApiService by lazy {
        BaseApp.API_SERVICE
    }

    fun getMatch(matchType: String?, leagueId: String = MatchFragment.LEAGUE_ID) {
        apiService.getMatch(matchType, leagueId)
                .getResponse({ response ->
                    response?.events?.let {
                        view.showMatch(matchList = it)
                    }
                }, {
                    view.onFailed(it)
                })
    }
}