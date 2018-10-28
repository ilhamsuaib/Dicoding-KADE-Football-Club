package id.ilhamsuaib.footballclub.data.remote.model

import com.google.gson.annotations.SerializedName
import id.ilhamsuaib.footballclub.model.Team

/**
 * Created by @ilhamsuaib on 06/10/18.
 * github.com/ilhamsuaib
 */

data class TeamModel(
        @field:SerializedName("idTeam") val idTeam: String?,
        @field:SerializedName("idSoccerXML") val idSoccerXML: String?,
        @field:SerializedName("intLoved") val intLoved: String?,
        @field:SerializedName("strTeam") val strTeam: String?,
        @field:SerializedName("strTeamShort") val strTeamShort: String?,
        @field:SerializedName("strAlternate") val strAlternate: String?,
        @field:SerializedName("intFormedYear") val intFormedYear: String?,
        @field:SerializedName("strSport") val strSport: String?,
        @field:SerializedName("strLeague") val strLeague: String?,
        @field:SerializedName("idLeague") val idLeague: String?,
        @field:SerializedName("strDivision") val strDivision: String?,
        @field:SerializedName("strDescriptionEN") val strDescriptionEN: String?,
        @field:SerializedName("strManager") val strManager: String?,
        @field:SerializedName("strStadium") val strStadium: String?,
        @field:SerializedName("strKeywords") val strKeywords: String?,
        @field:SerializedName("strRSS") val strRSS: String?,
        @field:SerializedName("strStadiumThumb") val strStadiumThumb: String?,
        @field:SerializedName("strStadiumDescription") val strStadiumDescription: String?,
        @field:SerializedName("strStadiumLocation") val strStadiumLocation: String?,
        @field:SerializedName("intStadiumCapacity") val intStadiumCapacity: String?,
        @field:SerializedName("strWebsite") val strWebsite: String?,
        @field:SerializedName("strFacebook") val strFacebook: String?,
        @field:SerializedName("strTwitter") val strTwitter: String?,
        @field:SerializedName("strInstagram") val strInstagram: String?,
        @field:SerializedName("strGender") val strGender: String?,
        @field:SerializedName("strCountry") val strCountry: String?,
        @field:SerializedName("strTeamBadge") val strTeamBadge: String?,
        @field:SerializedName("strTeamJersey") val strTeamJersey: String?,
        @field:SerializedName("strTeamLogo") val strTeamLogo: String?,
        @field:SerializedName("strTeamFanart1") val strTeamFanart1: String?,
        @field:SerializedName("strTeamFanart2") val strTeamFanart2: String?,
        @field:SerializedName("strTeamFanart3") val strTeamFanart3: String?,
        @field:SerializedName("strTeamFanart4") val strTeamFanart4: String?,
        @field:SerializedName("strTeamBanner") val strTeamBanner: String?,
        @field:SerializedName("strYoutube") val strYoutube: String?,
        @field:SerializedName("strLocked") val strLocked: String?
) {
    fun transform() = Team(
            idTeam = idTeam,
            strTeam = strTeam,
            strTeamBadge = strTeamBadge,
            intFormedYear = intFormedYear,
            strStadium = strStadium,
            strDescriptionEN = strDescriptionEN
    )
}