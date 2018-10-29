package id.ilhamsuaib.footballclub.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by @ilhamsuaib on 08/10/18.
 * github.com/ilhamsuaib
 */

@Parcelize
data class Match(val id: Long?,
                 val matchId: String?,
                 val homeTeamId: String?,
                 val awayTeamId: String?,
                 val homeTeamName: String?,
                 val awayTeamName: String?,
                 val homeScore: String?,
                 val awayScore: String?,
                 val strTime: String?,
                 val matchDate: String?): Parcelable