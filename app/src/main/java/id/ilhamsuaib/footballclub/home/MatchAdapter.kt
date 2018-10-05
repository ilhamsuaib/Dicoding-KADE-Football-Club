package id.ilhamsuaib.footballclub.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.data.model.MatchModel
import kotlinx.android.synthetic.main.adapter_match.view.*

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

class MatchAdapter(private val itemList: List<MatchModel>,
                   private val onItemClick: (match: MatchModel) -> Unit) : RecyclerView.Adapter<MatchAdapter.Holder>() {

    override fun onCreateViewHolder(group: ViewGroup, p: Int): Holder {
        val view = LayoutInflater.from(group.context)
                .inflate(R.layout.adapter_match, group, false)
        return Holder(view, onItemClick)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: Holder, p: Int) {
        holder.bind(itemList[p])
    }

    class Holder(itemView: View, private val onItemClick: (match: MatchModel) -> Unit)
        : RecyclerView.ViewHolder(itemView) {

        fun bind(match: MatchModel) {
            itemView.tvMatch.text = match.strFilename
            itemView.setOnClickListener {
                onItemClick(match)
            }
        }
    }
}