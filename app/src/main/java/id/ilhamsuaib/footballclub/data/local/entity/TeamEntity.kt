package id.ilhamsuaib.footballclub.data.local.entity

import id.ilhamsuaib.footballclub.model.Team

/**
 * Created by @ilhamsuaib on 29/10/18.
 * github.com/ilhamsuaib
 */

data class TeamEntity(
        val id: Long?,
        val idTeam: String?,
        val strTeam: String?,
        val strTeamBadge: String?,
        val intFormedYear: String?,
        val strStadium: String?,
        val strDescriptionEN: String?
) {
    companion object {
        const val FAVORITE_TEAM: String = "FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_BADGE = "TEAM_BADGE"
        const val FORMED_YEAR = "FORMED_YEAR"
        const val STADIUM = "STADIUM"
        const val DESCRIPTION_EN = "DESCRIPTION_EN"
    }

    fun transform() = Team(
            idTeam = idTeam,
            strTeam = strTeam,
            strTeamBadge = strTeamBadge,
            intFormedYear = intFormedYear,
            strStadium = strStadium,
            strDescriptionEN = strDescriptionEN
    )
}