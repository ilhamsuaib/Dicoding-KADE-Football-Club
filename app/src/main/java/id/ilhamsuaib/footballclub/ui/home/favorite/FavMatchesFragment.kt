package id.ilhamsuaib.footballclub.ui.home.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.model.Match
import id.ilhamsuaib.footballclub.ui.home.matches.MatchAdapter
import id.ilhamsuaib.footballclub.ui.matchDetail.MatchDetailActivity
import id.ilhamsuaib.footballclub.utilities.Const
import kotlinx.android.synthetic.main.fragment_fav_matches.view.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by @ilhamsuaib on 07/10/18.
 * github.com/ilhamsuaib
 */

class FavMatchesFragment : Fragment(), ServiceCallback {

    companion object {
        const val TAG = "FavoritesFragment"
        fun newInstance() = FavMatchesFragment()
    }

    private val presenter = FavoritePresenter()
    private val matchAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.bindCallback(this)
        return inflater.inflate(R.layout.fragment_fav_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rvFavoriteMatch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }
    }

    override fun onResume() {
        super.onResume()
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