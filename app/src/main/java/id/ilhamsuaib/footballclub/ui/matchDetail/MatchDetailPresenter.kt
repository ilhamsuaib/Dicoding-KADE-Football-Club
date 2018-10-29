package id.ilhamsuaib.footballclub.ui.matchDetail

import android.database.sqlite.SQLiteConstraintException
import id.ilhamsuaib.footballclub.base.BaseApp
import id.ilhamsuaib.footballclub.base.BasePresenter
import id.ilhamsuaib.footballclub.data.Repository
import id.ilhamsuaib.footballclub.data.local.DatabaseHelper
import id.ilhamsuaib.footballclub.data.local.entity.MatchEntity
import id.ilhamsuaib.footballclub.model.Match
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class MatchDetailPresenter(private val repo: Repository,
                           private val backgroundScheduler: Scheduler = Schedulers.io(),
                           private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()) : BasePresenter<ServiceCallback>() {

    fun getMatchDetail(idEvent: String?) {
        val obs = repo.getMatchDetail(matchId = idEvent)
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

        disposable.add(obs)
    }

    fun getTeamDetail(idHomeTeam: String?, idAwayTeam: String?) {
        getTeam(idHomeTeam = idHomeTeam)
        getTeam(idAwayTeam = idAwayTeam)
    }

    private fun getTeam(idHomeTeam: String? = null, idAwayTeam: String? = null) {
        val teamId = idHomeTeam ?: idAwayTeam
        val obs = repo.getTeamDetail(teamId)
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

        disposable.add(obs)
    }

    fun addToFavorite(match: Match) {
        DatabaseHelper.addFavMatch(match) {
            callback?.savedToFavorite()
        }
    }

    fun removeFromFavorite(matchId: String) {
        try {
            BaseApp.db?.use {
                delete(MatchEntity.FAVORITE_MATCH,
                        "(${MatchEntity.MATCH_ID} = {matchId})",
                        "matchId" to matchId)
            }
            callback?.removedToFavorite()
        } catch (ex: SQLiteConstraintException) {
            callback?.onFailed(ex.message!!)
        }
    }

    fun checkExistenceMatch(matchId: String) {
        BaseApp.db?.use {
            val results = select(MatchEntity.FAVORITE_MATCH, MatchEntity.MATCH_ID)
                    .whereArgs(MatchEntity.MATCH_ID + " = {${MatchEntity.MATCH_ID}}",
                            MatchEntity.MATCH_ID to matchId)
                    .limit(1)
                    .exec { parseList(classParser<String>()) }
            return@use callback?.savedAsFavorite(results.isNotEmpty())
        }
    }
}