package id.ilhamsuaib.footballclub.ui.matchDetail

import android.database.sqlite.SQLiteConstraintException
import id.ilhamsuaib.footballclub.base.BaseApp
import id.ilhamsuaib.footballclub.base.BasePresenter
import id.ilhamsuaib.footballclub.data.local.entity.FavoriteEntity
import id.ilhamsuaib.footballclub.model.Match
import id.ilhamsuaib.footballclub.utilities.getResponse
import id.ilhamsuaib.footballclub.utilities.logD
import id.ilhamsuaib.footballclub.utilities.toJson
import org.jetbrains.anko.db.insert

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

    fun addToFavorite(match: Match?) {
        try {
            BaseApp.db?.use {
                insert(FavoriteEntity.FAVORITE_MATCH,
                        FavoriteEntity.MATCH_ID to match?.matchId,
                        FavoriteEntity.HOME_TEAM_ID to match?.homeTeamId,
                        FavoriteEntity.AWAY_TEAM_ID to match?.awayTeamId,
                        FavoriteEntity.HOME_TEAM_NAME to match?.homeTeamName,
                        FavoriteEntity.AWAY_TEAM_NAME to match?.awayTeamName,
                        FavoriteEntity.HOME_SCORE to match?.homeScore,
                        FavoriteEntity.AWAY_SCORE to match?.awayScore,
                        FavoriteEntity.MATCH_DATE to match?.matchDate)
            }
            callback?.savedToFavorite()
        } catch (e: SQLiteConstraintException) {
            callback?.onFailed(e.message!!)
        }
    }

    fun removeFromFavorite(idEvent: String?) {
        callback?.removedToFavorite()
    }
}