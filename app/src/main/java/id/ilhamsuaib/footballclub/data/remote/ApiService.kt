package id.ilhamsuaib.footballclub.data.remote

import id.ilhamsuaib.footballclub.BuildConfig
import id.ilhamsuaib.footballclub.data.remote.model.MatchResponse
import id.ilhamsuaib.footballclub.data.remote.model.PlayerModel
import id.ilhamsuaib.footballclub.data.remote.model.PlayerResponse
import id.ilhamsuaib.footballclub.data.remote.model.TeamResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

interface ApiService {

    @GET("api/v1/json/${BuildConfig.API_KEY}/{matchType}")
    fun getMatch(@Path("matchType") matchType: String?,
                 @Query("id") leagueId: String): Observable<MatchResponse>

    @GET("api/v1/json/${BuildConfig.API_KEY}/lookupevent.php")
    fun getMatchDetail(@Query("id") matchId: String?): Observable<MatchResponse>

    @GET("api/v1/json/${BuildConfig.API_KEY}/lookupteam.php")
    fun getTeamDetail(@Query("id") teamId: String?): Observable<TeamResponse>

    @GET("api/v1/json/${BuildConfig.API_KEY}/search_all_teams.php")
    fun getLeagueTeams(@Query("l") strLeague: String): Observable<TeamResponse>

    @GET("api/v1/json/${BuildConfig.API_KEY}/lookup_all_players.php")
    fun getTeamPlayers(@Query("id") idTeam: String?): Observable<PlayerResponse>

    @GET("api/v1/json/${BuildConfig.API_KEY}/searchteams.php")
    fun searchTeam(@Query("t") teamName: String?): Observable<TeamResponse>

    @GET("api/v1/json/${BuildConfig.API_KEY}/searchevents.php")
    fun searchMatch(@Query("e") eventName: String?): Observable<MatchResponse>
}