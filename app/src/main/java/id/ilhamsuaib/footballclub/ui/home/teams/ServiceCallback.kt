package id.ilhamsuaib.footballclub.ui.home.teams

import id.ilhamsuaib.footballclub.base.BaseServiceCallback
import id.ilhamsuaib.footballclub.model.Team

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

interface ServiceCallback : BaseServiceCallback {
    fun showTeams(teamList: List<Team>)
    fun showProgress(show: Boolean)
    fun showSearchResult(teamList: List<Team>)
}