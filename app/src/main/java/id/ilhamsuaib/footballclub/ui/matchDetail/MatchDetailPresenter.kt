package id.ilhamsuaib.footballclub.ui.matchDetail

import android.database.sqlite.SQLiteConstraintException
import id.ilhamsuaib.footballclub.base.BaseApp
import id.ilhamsuaib.footballclub.base.BasePresenter
import id.ilhamsuaib.footballclub.data.Repository
import id.ilhamsuaib.footballclub.data.local.entity.FavoriteEntity
import id.ilhamsuaib.footballclub.model.Match
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.*

class MatchDetailPresenter(private val repo: Repository,
                           private val backgroundScheduler: Scheduler = Schedulers.io(),
                           private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()) : BasePresenter<ServiceCallback>() {

    fun getMatchDetail(idEvent: String?) {
        disposable.add(
                repo.getMatchDetail(matchId = idEvent)
                        .subscribeOn(backgroundScheduler)
                        .observeOn(mainScheduler)
                        .subscribe({
                            if (it.isNotEmpty())
                                callback?.showMatchDetail(it[0])
                            else
                                callback?.onFailed("No match event found")
                        }, {
                            callback?.onFailed(it.message!!)
                        })
        )
    }

    fun getTeamDetail(idHomeTeam: String?, idAwayTeam: String?) {
        getTeam(idHomeTeam = idHomeTeam)
        getTeam(idAwayTeam = idAwayTeam)
    }

    private fun getTeam(idHomeTeam: String? = null, idAwayTeam: String? = null) {
        val teamId = idHomeTeam ?: idAwayTeam
        disposable.add(
                repo.getTeamDetail(teamId)
                        .subscribeOn(backgroundScheduler)
                        .observeOn(mainScheduler)
                        .subscribe({ itemList ->
                            if (itemList.isNotEmpty()) {
                                idHomeTeam?.let {
                                    callback?.showHomeTeam(itemList[0])
                                }
                                idAwayTeam?.let {
                                    callback?.showAwayTeam(itemList[0])
                                }
                            } else {
                                callback?.onFailed("No team found with id : $teamId")
                            }
                        }, {
                            callback?.onFailed(it.message!!)
                        })
        )
    }

    fun addToFavorite(match: Match) {
        try {
            BaseApp.db?.use {
                insert(FavoriteEntity.FAVORITE_MATCH,
                        FavoriteEntity.MATCH_ID to match.matchId,
                        FavoriteEntity.HOME_TEAM_ID to match.homeTeamId,
                        FavoriteEntity.AWAY_TEAM_ID to match.awayTeamId,
                        FavoriteEntity.HOME_TEAM_NAME to match.homeTeamName,
                        FavoriteEntity.AWAY_TEAM_NAME to match.awayTeamName,
                        FavoriteEntity.HOME_SCORE to match.homeScore,
                        FavoriteEntity.AWAY_SCORE to match.awayScore,
                        FavoriteEntity.MATCH_DATE to match.matchDate)
            }
            callback?.savedToFavorite()
        } catch (e: SQLiteConstraintException) {
            callback?.onFailed(e.message!!)
        }
    }

    fun removeFromFavorite(matchId: String) {
        try {
            BaseApp.db?.use {
                delete(FavoriteEntity.FAVORITE_MATCH,
                        "(${FavoriteEntity.MATCH_ID} = {matchId})",
                        "matchId" to matchId)
            }
            callback?.removedToFavorite()
        } catch (ex: SQLiteConstraintException) {
            callback?.onFailed(ex.message!!)
        }
    }

    fun checkExistenceMatch(matchId: String) {
        BaseApp.db?.use {
            val results = select(FavoriteEntity.FAVORITE_MATCH, FavoriteEntity.MATCH_ID)
                    .whereArgs(FavoriteEntity.MATCH_ID + " = {${FavoriteEntity.MATCH_ID}}",
                            FavoriteEntity.MATCH_ID to matchId)
                    .limit(1)
                    .exec { parseList(classParser<String>()) }
            return@use callback?.savedAsFavorite(results.isNotEmpty())
        }
    }
}