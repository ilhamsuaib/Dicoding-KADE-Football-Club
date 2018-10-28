package id.ilhamsuaib.footballclub.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */


@Parcelize
data class Team(
        val idTeam: String?,
        val strTeam: String?,
        val strTeamBadge: String?,
        val intFormedYear: String?,
        val strStadium: String?,
        val strDescriptionEN: String?
) : Parcelable