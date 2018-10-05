package id.ilhamsuaib.footballclub.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

data class MatchModel(
        @field:SerializedName("idEvent")
        val idEvent: String? = null,
        @field:SerializedName("idSoccerXML")
        val idSoccerXML: String? = null,
        @field:SerializedName("strEvent")
        val strEvent: String? = null,
        @field:SerializedName("strFilename")
        val strFilename: String? = null,
        @field:SerializedName("strSport")
        val strSport: String? = null,
        @field:SerializedName("idLeague")
        val idLeague: String? = null,
        @field:SerializedName("strLeague")
        val strLeague: String? = null,
        @field:SerializedName("strSeason")
        val strSeason: String? = null,
        @field:SerializedName("strDescriptionEN")
        val strDescriptionEN: String? = null,
        @field:SerializedName("strHomeTeam")
        val strHomeTeam: String? = null,
        @field:SerializedName("strAwayTeam")
        val strAwayTeam: String? = null,
        @field:SerializedName("intHomeScore")
        val intHomeScore: String? = null,
        @field:SerializedName("intRound")
        val intRound: String? = null,
        @field:SerializedName("intAwayScore")
        val intAwayScore: String? = null,
        @field:SerializedName("intSpectators")
        val intSpectators: String? = null,
        @field:SerializedName("strHomeGoalDetails")
        val strHomeGoalDetails: String? = null,
        @field:SerializedName("strHomeRedCards")
        val strHomeRedCards: String,
        @field:SerializedName("strHomeYellowCards")
        val strHomeYellowCards: String,
        @field:SerializedName("strHomeLineupGoalkeeper")
        val strHomeLineupGoalkeeper: String,
        @field:SerializedName("strHomeLineupDefense")
        val strHomeLineupDefense: String? = null,
        @field:SerializedName("strHomeLineupMidfield")
        val strHomeLineupMidfield: String? = null,
        @field:SerializedName("strHomeLineupForward")
        val strHomeLineupForward: String? = null,
        @field:SerializedName("strHomeLineupSubstitutes")
        val strHomeLineupSubstitutes: String? = null,
        @field:SerializedName("strHomeFormation")
        val strHomeFormation: String? = null,
        @field:SerializedName("strAwayRedCards")
        val strAwayRedCards: String? = null,
        @field:SerializedName("strAwayYellowCards")
        val strAwayYellowCards: String? = null,
        @field:SerializedName("strAwayGoalDetails")
        val strAwayGoalDetails: String? = null,
        @field:SerializedName("strAwayLineupGoalkeeper")
        val strAwayLineupGoalkeeper: String? = null,
        @field:SerializedName("strAwayLineupDefense")
        val strAwayLineupDefense: String? = null,
        @field:SerializedName("strAwayLineupMidfield")
        val strAwayLineupMidfield: String? = null,
        @field:SerializedName("strAwayLineupForward")
        val strAwayLineupForward: String? = null,
        @field:SerializedName("strAwayLineupSubstitutes")
        val strAwayLineupSubstitutes: String? = null,
        @field:SerializedName("strAwayFormation")
        val strAwayFormation: String? = null,
        @SerializedName("intHomeShots")
        val intHomeShots: String? = null,
        @SerializedName("intAwayShots")
        val intAwayShots: String? = null,
        @field:SerializedName("dateEvent")
        val dateEvent: String? = null,
        @field:SerializedName("strDate")
        val strDate: String? = null,
        @field:SerializedName("strTime")
        val strTime: String? = null,
        @field:SerializedName("strTVStation")
        val strTVStation: String? = null,
        @field:SerializedName("idHomeTeam")
        val idHomeTeam: String? = null,
        @field:SerializedName("idAwayTeam")
        val idAwayTeam: String? = null,
        @field:SerializedName("strResult")
        val strResult: String? = null,
        @field:SerializedName("strCircuit")
        val strCircuit: String? = null,
        @field:SerializedName("strCountry")
        val strCountry: String? = null,
        @field:SerializedName("strCity")
        val strCity: String? = null,
        @field:SerializedName("strPoster")
        val strPoster: String? = null,
        @field:SerializedName("strFanart")
        val strFanart: String? = null,
        @field:SerializedName("strThumb")
        val strThumb: String? = null,
        @field:SerializedName("strBanner")
        val strBanner: String? = null,
        @field:SerializedName("strMap")
        val strMap: String? = null,
        @field:SerializedName("strLocked")
        val strLocked: String? = null
)