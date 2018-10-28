package id.ilhamsuaib.footballclub.ui.teamDetail

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.model.Player
import id.ilhamsuaib.footballclub.utilities.loadImage
import kotlinx.android.synthetic.main.item_player.view.*

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

class PlayerAdapter(private val player: Player,
                    private val onItemClick: () -> Unit) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val view = viewHolder.itemView
        view.imgAvatar.loadImage(player.strCutout)
        view.tvPlayerName.text = player.strPlayer
        view.tvPosition.text = player.strPosition

        view.setOnClickListener {
            onItemClick()
        }
    }

    override fun getLayout(): Int = R.layout.item_player
}