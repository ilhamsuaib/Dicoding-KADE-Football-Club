package id.ilhamsuaib.footballclub.ui.matchDetail

import id.ilhamsuaib.footballclub.base.BaseServiceCallback
import id.ilhamsuaib.footballclub.data.remote.model.MatchModel
import id.ilhamsuaib.footballclub.data.remote.model.TeamModel

/**
 * Created by @ilhamsuaib on 06/10/18.
 * github.com/ilhamsuaib
 */

interface ServiceCallback : BaseServiceCallback {

    fun showMatchDetail(match: MatchModel)
    fun onFailed(message: String)
    fun showHomeTeam(team: TeamModel)
    fun showAwayTeam(team: TeamModel)
    fun savedToFavorite()
    fun removedToFavorite()
    fun savedAsFavorite(saved: Boolean)
}