package id.ilhamsuaib.footballclub.data.local.entity

import id.ilhamsuaib.footballclub.model.Match

/**
 * Created by @ilhamsuaib on 08/10/18.
 * github.com/ilhamsuaib
 */

data class FavoriteEntity(val id: Long?,
                          val matchId: String?,
                          val homeTeamId: String?,
                          val awayTeamId: String?,
                          val homeTeamName: String?,
                          val awayTeamName: String?,
                          val homeScore: String?,
                          val awayScore: String?,
                          val matchDate: String?) {

    companion object {
        const val FAVORITE_MATCH: String = "FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val MATCH_DATE: String = "MATCH_DATE"
    }

    fun transform(): Match {
        return Match(
                id = this.id,
                matchId = this.matchId,
                homeTeamId = this.homeTeamId,
                awayTeamId = this.awayTeamId,
                homeTeamName = this.homeTeamName,
                awayTeamName = this.awayTeamName,
                homeScore = this.homeScore,
                awayScore = this.awayScore,
                matchDate = this.matchDate
        )
    }
}