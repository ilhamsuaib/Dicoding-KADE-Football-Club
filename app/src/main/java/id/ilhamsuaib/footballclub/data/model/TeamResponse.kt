package id.ilhamsuaib.footballclub.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by @ilhamsuaib on 07/10/18.
 * github.com/ilhamsuaib
 */

class TeamResponse {
    @field:SerializedName("teams")
    val teams: List<TeamModel>? = emptyList()
}