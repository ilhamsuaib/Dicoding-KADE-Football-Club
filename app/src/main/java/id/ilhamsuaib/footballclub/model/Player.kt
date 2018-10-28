package id.ilhamsuaib.footballclub.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

@Parcelize
data class Player(
        val idPlayer: String?,
        val strPlayer: String?,
        val strDescriptionEN: String?,
        val strPosition: String?,
        val strHeight: String?,
        val strWeight: String?,
        val strFanart1: String?,
        val strCutout: String?
) : Parcelable