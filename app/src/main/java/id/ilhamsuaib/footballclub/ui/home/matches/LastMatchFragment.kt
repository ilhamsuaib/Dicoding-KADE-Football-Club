package id.ilhamsuaib.footballclub.ui.home.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.data.Repository
import id.ilhamsuaib.footballclub.model.Match
import id.ilhamsuaib.footballclub.ui.matchDetail.MatchDetailActivity
import id.ilhamsuaib.footballclub.utilities.Const
import id.ilhamsuaib.footballclub.utilities.addOnItemSelectedListener
import kotlinx.android.synthetic.main.fragment_last_match.view.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

class LastMatchFragment : Fragment(), ServiceCallback {

    companion object {
        private const val TAG = "LastMatchFragment"
    }

    private val presenter = MatchPresenter(Repository())
    private val matchAdapter = GroupAdapter<ViewHolder>()
    private val matchList = mutableListOf<Match>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.bindCallback(this)
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)
    }

    private fun setupView(view: View) {
        val spinnerAdapter = ArrayAdapter<String>(view.context, android.R.layout.simple_spinner_dropdown_item, getLeagueList())
        view.spLeagueLastMatch.apply {
            adapter = spinnerAdapter
            addOnItemSelectedListener {
                getMatchList(position = it)
            }
        }

        view.rvLastMatch?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }

        getMatchList()
    }

    private fun getMatchList(position: Int = 0) {
        val matchType = "eventspastleague.php"
        val leagueId = Const.LEAGUES[position].idLeague
        presenter.getMatch(matchType = matchType, leagueId = leagueId)
    }

    private fun getLeagueList(): Array<String> {
        val leagues = mutableListOf<String>()
        Const.LEAGUES.forEach {
            leagues.add(it.strLeague)
        }
        return leagues.toTypedArray()
    }

    override fun showMatch(matchList: List<Match>) {
        this.matchList.clear()
        this.matchList.addAll(matchList)
        setAdapterItems(matchList)
    }

    private fun setAdapterItems(matchList: List<Match>) {
        matchAdapter.clear()
        matchList.forEach {
            matchAdapter.add(MatchAdapter(it) {
                startActivity<MatchDetailActivity>(Const.MATCH to it)
            })
        }
    }

    override fun showProgress(show: Boolean) {
        view?.progress?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }
}