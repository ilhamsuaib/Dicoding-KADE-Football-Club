package id.ilhamsuaib.footballclub.matchDetail

import id.ilhamsuaib.footballclub.data.model.MatchModel
import id.ilhamsuaib.footballclub.data.model.TeamModel

/**
 * Created by @ilhamsuaib on 06/10/18.
 * github.com/ilhamsuaib
 */

interface ServiceCallback {

    fun showMatchDetail(match: MatchModel)
    fun onFailed(message: String)
    fun showHomeTeam(team: TeamModel)
    fun showAwayTeam(team: TeamModel)
}