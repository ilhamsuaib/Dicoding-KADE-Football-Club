package id.ilhamsuaib.footballclub.ui.home.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.data.Repository
import id.ilhamsuaib.footballclub.model.Match
import id.ilhamsuaib.footballclub.ui.matchDetail.MatchDetailActivity
import id.ilhamsuaib.footballclub.utilities.Const
import kotlinx.android.synthetic.main.fragment_next_match.view.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by @ilhamsuaib on 14/10/18.
 * github.com/ilhamsuaib
 */

class NextMatchFragment : Fragment(), ServiceCallback {

    private val presenter = MatchPresenter(Repository())
    private val matchAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.bindCallback(this)
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.rvNextMatch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }

        val matchType = "eventsnextleague.php"
        presenter.getMatch(matchType = matchType)
    }

    override fun showMatch(matchList: List<Match>) {
        matchAdapter.clear()
        matchList.forEach {
            matchAdapter.add(MatchAdapter(it) {
                startActivity<MatchDetailActivity>(Const.MATCH to it)
            })
        }
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }
}