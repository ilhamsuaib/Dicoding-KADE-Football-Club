package id.ilhamsuaib.footballclub.data

import id.ilhamsuaib.footballclub.data.remote.ApiService
import id.ilhamsuaib.footballclub.data.remote.model.MatchModel
import id.ilhamsuaib.footballclub.data.remote.model.TeamModel
import id.ilhamsuaib.footballclub.model.Match
import id.ilhamsuaib.footballclub.model.Player
import id.ilhamsuaib.footballclub.model.Team
import id.ilhamsuaib.footballclub.utilities.NetworkConfig
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by @ilhamsuaib on 11/10/18.
 * github.com/ilhamsuaib
 */

class Repository {

    private val apiService: ApiService by lazy {
        NetworkConfig.getApiService()
    }

    fun getMatch(matchType: String?, leagueId: String): Single<List<Match>> {
        return apiService.getMatch(matchType, leagueId)
                .flatMapIterable {
                    it.events
                }
                .map {
                    it.transform()
                }
                .toList()
    }

    fun getMatchDetail(matchId: String?): Observable<List<MatchModel>> {
        return apiService.getMatchDetail(matchId)
                .map { it.events }
    }

    fun getTeamDetail(teamId: String?): Observable<List<TeamModel>> {
        return apiService.getTeamDetail(teamId)
                .map { it.teams }
    }

    fun getTeams(strLeague: String): Single<List<Team>> {
        return apiService.getLeagueTeams(strLeague)
                .flatMapIterable {
                    it.teams
                }
                .map {
                    it.transform()
                }
                .toList()
    }

    fun getTeamPlayer(idTeam: String?): Single<List<Player>> {
        return apiService.getTeamPlayers(idTeam)
                .flatMapIterable {
                    it.player
                }
                .map {
                    it.transform()
                }
                .toList()
    }
}