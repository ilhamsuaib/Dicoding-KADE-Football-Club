package id.ilhamsuaib.footballclub.model

import com.google.gson.annotations.SerializedName

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

data class MatchResponse(
        @field:SerializedName("events")
        val events: List<MatchModel> = emptyList()
)