package id.ilhamsuaib.footballclub.ui.home.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.model.Match
import kotlinx.android.synthetic.main.fragment_match.view.*
import id.ilhamsuaib.footballclub.ui.home.match.MatchAdapter
import id.ilhamsuaib.footballclub.ui.matchDetail.MatchDetailActivity
import org.jetbrains.anko.support.v4.startActivity
import id.ilhamsuaib.footballclub.utilities.Const

/**
 * Created by @ilhamsuaib on 07/10/18.
 * github.com/ilhamsuaib
 */

class FavoritesFragment : Fragment(), ServiceCallback {

    companion object {
        fun newInstance() = FavoritesFragment()
        const val TAG = "FavoritesFragment"
    }

    private val presenter = FavoritePresenter()
    private val matchAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.bindCallback(this)
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rvMatch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }

        presenter.getFavorites()
    }

    override fun showFavorites(matchList: List<Match>) {
        matchAdapter.clear()
        matchList.forEach {
            matchAdapter.add(MatchAdapter(it) {
                startActivity<MatchDetailActivity>(Const.MATCH to it)
            })
        }
    }
}