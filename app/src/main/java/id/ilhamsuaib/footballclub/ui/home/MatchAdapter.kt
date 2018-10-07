package id.ilhamsuaib.footballclub.ui.home

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.model.MatchModel
import id.ilhamsuaib.footballclub.utilities.parseDate
import kotlinx.android.synthetic.main.adapter_match.view.*

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

class MatchAdapter(private val match: MatchModel,
                   private val onItemClick: () -> Unit): Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val itemView = viewHolder.itemView
        itemView.tvDateTime.text = match.dateEvent?.parseDate("yyyy-MM-dd")
        itemView.tvHomeTeam.text = match.strHomeTeam
        itemView.tvAwayTeam.text = match.strAwayTeam
        itemView.tvHomeScore.text = match.intHomeScore ?: "?"
        itemView.tvAwayScore.text = match.intAwayScore ?: "?"
        itemView.setOnClickListener {
            onItemClick()
        }
    }

    override fun getLayout(): Int = R.layout.adapter_match
}