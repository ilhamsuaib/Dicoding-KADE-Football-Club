package id.ilhamsuaib.footballclub.ui.matchDetail

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import kotlinx.android.synthetic.main.item_detail_header.view.*

/**
 * Created by @ilhamsuaib on 07/10/18.
 * github.com/ilhamsuaib
 */

class HeaderAdapter(val title: String) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tvHeaderTitle.text = title
    }

    override fun getLayout(): Int = R.layout.item_detail_header
}