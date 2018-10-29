package id.ilhamsuaib.footballclub.ui.teamDetail

import id.ilhamsuaib.footballclub.base.BaseServiceCallback
import id.ilhamsuaib.footballclub.model.Player

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

interface ServiceCallback : BaseServiceCallback {
    fun showPlayers(playerList: List<Player>) {}
    fun showProgress(show: Boolean) {}
    fun savedAsFavorite(b: Boolean) {}
    fun onAddedToFavorite() {}
    fun onRemovedFromFavorite() {}
    fun showTeamOverview(overview: String) {}
}