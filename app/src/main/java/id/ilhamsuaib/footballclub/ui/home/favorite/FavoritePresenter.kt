package id.ilhamsuaib.footballclub.ui.home.favorite

import id.ilhamsuaib.footballclub.base.BaseApp
import id.ilhamsuaib.footballclub.base.BasePresenter
import id.ilhamsuaib.footballclub.data.local.entity.MatchEntity
import id.ilhamsuaib.footballclub.data.local.entity.TeamEntity
import id.ilhamsuaib.footballclub.utilities.logD
import id.ilhamsuaib.footballclub.utilities.toJson
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

/**
 * Created by @ilhamsuaib on 08/10/18.
 * github.com/ilhamsuaib
 */

class FavoritePresenter : BasePresenter<ServiceCallback>() {

    fun getFavoriteMatches() {
        BaseApp.db?.use {
            val results = select(MatchEntity.FAVORITE_MATCH)
                    .exec { parseList(classParser<MatchEntity>()) }

            logD(FavMatchesFragment.TAG, "favorites : ${results.toJson()}")
            val matchList = results.map {
                it.transform()
            }

            return@use callback?.showFavoriteMatches(matchList)
        }
    }

    fun getFavoriteTeams() {
        BaseApp.db?.use {
            val results = select(TeamEntity.FAVORITE_TEAM)
                    .exec { parseList(classParser<TeamEntity>()) }

            logD(FavMatchesFragment.TAG, "favorites : ${results.toJson()}")
            val matchList = results.map {
                it.transform()
            }

            return@use callback?.showFavoriteTeams(matchList)
        }
    }
}