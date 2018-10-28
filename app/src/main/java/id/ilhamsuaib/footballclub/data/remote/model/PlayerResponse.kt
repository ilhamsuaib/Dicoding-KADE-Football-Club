package id.ilhamsuaib.footballclub.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

data class PlayerResponse(@field:SerializedName("player") val player: List<PlayerModel> = emptyList())