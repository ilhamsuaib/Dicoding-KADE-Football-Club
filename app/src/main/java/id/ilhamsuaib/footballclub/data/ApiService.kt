package id.ilhamsuaib.footballclub.data

import id.ilhamsuaib.footballclub.BuildConfig
import id.ilhamsuaib.footballclub.data.model.MatchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

interface ApiService {

    @GET("${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.API_KEY}/{matchType}")
    fun getMatch(@Path("matchType") type: String?,
                 @Query("id") leagueId: String): Call<MatchResponse>
}