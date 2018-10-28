package id.ilhamsuaib.footballclub.ui.home.teams

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.model.Team
import id.ilhamsuaib.footballclub.utilities.loadImage
import kotlinx.android.synthetic.main.item_team.view.*

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

class TeamAdapter(private val team: Team,
                  private val onItemClick: () -> Unit) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val view = viewHolder.itemView
        view.imgLogo.loadImage(team.strTeamBadge)
        view.tvClubName.text = team.strTeam
        view.setOnClickListener {
            onItemClick()
        }
    }

    override fun getLayout(): Int = R.layout.item_team
}