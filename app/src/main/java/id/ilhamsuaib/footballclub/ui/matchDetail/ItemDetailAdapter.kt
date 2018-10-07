package id.ilhamsuaib.footballclub.ui.matchDetail

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.model.MatchDetailModel
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * Created by @ilhamsuaib on 07/10/18.
 * github.com/ilhamsuaib
 */

class ItemDetailAdapter(private val item: MatchDetailModel) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val view = viewHolder.itemView
        view.tvLeftText.text = item.leftText
        view.tvMidText.text = item.midText
        view.tvRightText.text = item.rightText
    }

    override fun getLayout(): Int = R.layout.item_detail
}