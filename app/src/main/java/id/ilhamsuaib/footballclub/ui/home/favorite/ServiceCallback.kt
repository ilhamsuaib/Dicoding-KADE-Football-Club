package id.ilhamsuaib.footballclub.ui.home.favorite

import id.ilhamsuaib.footballclub.base.BaseServiceCallback
import id.ilhamsuaib.footballclub.model.Match
import id.ilhamsuaib.footballclub.model.Team

/**
 * Created by @ilhamsuaib on 08/10/18.
 * github.com/ilhamsuaib
 */

interface ServiceCallback : BaseServiceCallback {
    fun showFavoriteMatches(matchList: List<Match>) {}
    fun showFavoriteTeams(teamList: List<Team>) {}
}