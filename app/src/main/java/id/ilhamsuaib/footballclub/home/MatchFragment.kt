package id.ilhamsuaib.footballclub.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.data.model.MatchModel
import kotlinx.android.synthetic.main.fragment_match.view.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

class MatchFragment : Fragment(), MatchView {

    companion object {
        private const val TAG = "MatchFragment"
        private const val MATCH_TYPE = "matchType"
        const val LEAGUE_ID = "4335"

        fun newInstance(matchType: String): MatchFragment {
            val arguments = Bundle()
            arguments.putString(MATCH_TYPE, matchType)
            val fragment = MatchFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    private val presenter = MatchPresenter(this)
    private val matchList = mutableListOf<MatchModel>()
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val matchType = arguments?.getString(MATCH_TYPE)

        matchAdapter = MatchAdapter(matchList) { match ->
            match.idHomeTeam?.let { toast(it) }
        }

        view.rvMatch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }

        presenter.getMatch(matchType = matchType)
    }

    override fun showMatch(matchList: List<MatchModel>) {
        this.matchList.addAll(matchList)
        matchAdapter.notifyDataSetChanged()
    }

    override fun onFailed(message: String) {
        toast(message)
    }
}