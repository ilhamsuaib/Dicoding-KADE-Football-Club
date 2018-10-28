package id.ilhamsuaib.footballclub.data.remote.model

import com.google.gson.annotations.SerializedName
import id.ilhamsuaib.footballclub.model.Player

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

data class PlayerModel(
        @field:SerializedName("idPlayer") val idPlayer: String?,
        @field:SerializedName("strPlayer") val strPlayer: String?,
        @field:SerializedName("strDescriptionEN") val strDescriptionEN: String?,
        @field:SerializedName("strPosition") val strPosition: String?,
        @field:SerializedName("strHeight") val strHeight: String?,
        @field:SerializedName("strWeight") val strWeight: String?,
        @field:SerializedName("strFanart1") val strFanart1: String?,
        @field:SerializedName("strCutout") val strCutout: String?
) {
    fun transform() = Player(
            idPlayer = idPlayer,
            strPlayer = strPlayer,
            strDescriptionEN = strDescriptionEN,
            strPosition = strPosition,
            strHeight = strHeight,
            strWeight = strWeight,
            strFanart1 = strFanart1,
            strCutout = strCutout
    )
}