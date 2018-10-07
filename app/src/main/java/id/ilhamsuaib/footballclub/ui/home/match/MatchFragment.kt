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
import id.ilhamsuaib.footballclub.model.Match
import id.ilhamsuaib.footballclub.ui.matchDetail.MatchDetailActivity
import id.ilhamsuaib.footballclub.utilities.Const
import kotlinx.android.synthetic.main.fragment_match.view.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

class MatchFragment : Fragment(), ServiceCallback {

    companion object {
        private const val MATCH_TYPE = "matchType"
        const val LEAGUE_ID = "4335"

        fun newInstance(matchType: String): MatchFragment {
            return MatchFragment().apply {
                arguments = Bundle().apply {
                    putString(MATCH_TYPE, matchType)
                }
            }
        }
    }

    private val presenter = MatchPresenter()
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

        val matchType = arguments?.getString(MATCH_TYPE)
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

    override fun onFailed(message: String) {
        toast(message)
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }
}