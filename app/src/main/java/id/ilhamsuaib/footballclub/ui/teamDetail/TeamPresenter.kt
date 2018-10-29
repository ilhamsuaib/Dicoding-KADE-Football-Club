package id.ilhamsuaib.footballclub.ui.teamDetail

import android.database.sqlite.SQLiteConstraintException
import id.ilhamsuaib.footballclub.base.BaseApp
import id.ilhamsuaib.footballclub.base.BasePresenter
import id.ilhamsuaib.footballclub.data.Repository
import id.ilhamsuaib.footballclub.data.local.DatabaseHelper
import id.ilhamsuaib.footballclub.data.local.entity.TeamEntity
import id.ilhamsuaib.footballclub.model.Team
import id.ilhamsuaib.footballclub.utilities.logD
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

class TeamPresenter(private val repo: Repository,
                    private val backgroundScheduler: Scheduler = Schedulers.io(),
                    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()) : BasePresenter<ServiceCallback>() {

    fun getTeamPlayers(idTeam: String?) {
        callback?.showProgress(true)
        val obs = repo.getTeamPlayer(idTeam)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe({
                    callback?.showPlayers(it)
                    callback?.showProgress(false)
                }, {
                    it.printStackTrace()
                    callback?.showProgress(false)
                })

        disposable.add(obs)
    }

    fun checkExistenceTeam(teamId: String) {
        BaseApp.db?.use {
            val results = select(TeamEntity.FAVORITE_TEAM, TeamEntity.TEAM_ID)
                    .whereArgs(TeamEntity.TEAM_ID + " = {${TeamEntity.TEAM_ID}}",
                            TeamEntity.TEAM_ID to teamId)
                    .limit(1)
                    .exec { parseList(classParser<String>()) }
            logD("tag", "")
            return@use callback?.savedAsFavorite(results.isNotEmpty())
        }
    }

    fun addToFavorite(team: Team?) {
        team?.let {
            DatabaseHelper.addFavTeam(it) {
                callback?.onAddedToFavorite()
            }
        }
    }

    fun removeFromFavorite(idTeam: String) {
        try {
            BaseApp.db?.use {
                delete(TeamEntity.FAVORITE_TEAM,
                        "(${TeamEntity.TEAM_ID} = {${TeamEntity.TEAM_ID}})",
                        TeamEntity.TEAM_ID to idTeam)
            }
            callback?.onRemovedFromFavorite()
        } catch (ex: SQLiteConstraintException) {
            ex.printStackTrace()
        }
    }
}