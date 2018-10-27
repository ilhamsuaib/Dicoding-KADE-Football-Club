package id.ilhamsuaib.footballclub.ui.home.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.ArrayAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.data.Repository
import id.ilhamsuaib.footballclub.model.Match
import id.ilhamsuaib.footballclub.ui.matchDetail.MatchDetailActivity
import id.ilhamsuaib.footballclub.utilities.Const
import id.ilhamsuaib.footballclub.utilities.addOnItemSelecterListener
import id.ilhamsuaib.footballclub.utilities.logD
import kotlinx.android.synthetic.main.fragment_next_match.view.*
import org.jetbrains.anko.sdk27.coroutines.onItemClick
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by @ilhamsuaib on 14/10/18.
 * github.com/ilhamsuaib
 */

class NextMatchFragment : Fragment(), ServiceCallback {

    companion object {
        private const val TAG = "NextMatchFragment"
    }
    private val presenter = MatchPresenter(Repository())
    private val matchAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.bindCallback(this)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)
    }

    private fun setupView(view: View) {
        val spinnerAdapter = ArrayAdapter<String>(view.context, android.R.layout.simple_spinner_dropdown_item, getLeagueList())
        view.spLeague.apply {
            adapter = spinnerAdapter
            addOnItemSelecterListener {
                getMatchList(position = it)
            }
        }

        view.rvNextMatch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }

        getMatchList()
    }

    private fun getLeagueList(): Array<String> {
        val leagues = mutableListOf<String>()
        Const.LEAGUES.forEach {
            leagues.add(it.strLeague)
        }
        return leagues.toTypedArray()
    }

    private fun getMatchList(position: Int = 0) {
        val matchType = "eventsnextleague.php"
        val leagueId = Const.LEAGUES[position].idLeague
        presenter.getMatch(matchType = matchType, leagueId = leagueId)
    }

    override fun showMatch(matchList: List<Match>) {
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.menu_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                logD(TAG, "onQueryTextSubmit : $s")
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                logD(TAG, "onQueryTextChange : $s")
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}