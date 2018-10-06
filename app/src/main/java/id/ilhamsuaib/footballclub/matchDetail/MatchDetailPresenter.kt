package id.ilhamsuaib.footballclub.matchDetail

import id.ilhamsuaib.footballclub.base.BasePresenter
import id.ilhamsuaib.footballclub.utilities.getResponse
import id.ilhamsuaib.footballclub.utilities.logD
import id.ilhamsuaib.footballclub.utilities.toJson

class MatchDetailPresenter : BasePresenter<ServiceCallback>() {

    fun getMatchDetail(idEvent: String?) {
        apiService.getMatchDetail(matchId = idEvent)
                .getResponse({
                    callback?.onFailed(it)
                }, {
                    if (it?.events?.isNotEmpty() == true)
                        callback?.showMatchDetail(it.events[0])
                    else
                        callback?.onFailed("No match event found")
                })
    }

    fun getTeamDetail(idHomeTeam: String?, idAwayTeam: String?) {
        getTeam(idHomeTeam = idHomeTeam)
        getTeam(idAwayTeam = idAwayTeam)
    }

    private fun getTeam(idHomeTeam: String? = null, idAwayTeam: String? = null) {
        val teamId = idHomeTeam ?: idAwayTeam
        apiService.getTeamDetail(teamId)
                .getResponse({
                    callback?.onFailed(it)
                }, { response ->
                    logD(s = "${response.toJson()}")
                    if (response?.teams?.isNotEmpty() == true) {
                        idHomeTeam?.let {
                            callback?.showHomeTeam(response.teams[0])
                        }
                        idAwayTeam?.let {
                            callback?.showAwayTeam(response.teams[0])
                        }
                    } else {
                        callback?.onFailed("No team found with id : $teamId")
                    }
                })
    }
}