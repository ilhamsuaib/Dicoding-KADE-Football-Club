package id.ilhamsuaib.footballclub.ui.home.matches

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
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
import id.ilhamsuaib.footballclub.utilities.addOnItemSelectedListener
import id.ilhamsuaib.footballclub.utilities.getTimeMillis
import id.ilhamsuaib.footballclub.utilities.logD
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
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)
    }

    private fun setupView(view: View) {
        val spinnerAdapter = ArrayAdapter<String>(view.context, android.R.layout.simple_spinner_dropdown_item, getLeagueList())
        view.spLeague.apply {
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
            matchAdapter.add(
                    MatchAdapter(it) {
                        startActivity<MatchDetailActivity>(Const.MATCH to it)
                    }
            )
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

        searchItem?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                logD(TAG, "onMenuItemActionExpand : ")
                setAdapterItems(emptyList())
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                logD(TAG, "onMenuItemActionCollapse : ")
                setAdapterItems(matchList)
                return true
            }
        })

        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(queryTextListener())
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun queryTextListener(): SearchView.OnQueryTextListener? {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                logD(TAG, "onQueryTextSubmit : $s")
                filterAdapterItem(s?.toLowerCase())
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                logD(TAG, "onQueryTextChange : $s")
                filterAdapterItem(s?.toLowerCase())
                return false
            }
        }
    }

    private fun filterAdapterItem(s: String?) {
        if (s.isNullOrBlank()) {
            return
        }
        val newMatchList = matchList.filter {
            " ${it.homeTeamName}".toLowerCase().contains(" ${s?.toLowerCase()}") ||
                    " ${it.awayTeamName}".toLowerCase().contains(" ${s?.toLowerCase()}")
        }

        setAdapterItems(newMatchList)
    }
}