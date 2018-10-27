package id.ilhamsuaib.footballclub.ui.home.matches

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.model.Match
import id.ilhamsuaib.footballclub.utilities.parseDate
import kotlinx.android.synthetic.main.adapter_match.view.*

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

class MatchAdapter(private val match: Match,
                   private val onItemClick: () -> Unit) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val itemView = viewHolder.itemView
        itemView.tvDateTime.text = match.matchDate?.parseDate("yyyy-MM-dd")
        itemView.tvHomeTeam.text = match.homeTeamName
        itemView.tvAwayTeam.text = match.awayTeamName
        itemView.tvHomeScore.text = match.homeScore ?: "?"
        itemView.tvAwayScore.text = match.awayScore ?: "?"
        itemView.setOnClickListener {
            onItemClick()
        }
    }

    override fun getLayout(): Int = R.layout.adapter_match
}