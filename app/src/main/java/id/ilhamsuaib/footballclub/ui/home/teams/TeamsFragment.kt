package id.ilhamsuaib.footballclub.ui.home.teams

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
import id.ilhamsuaib.footballclub.model.Team
import id.ilhamsuaib.footballclub.ui.teamDetail.TeamDetailActivity
import id.ilhamsuaib.footballclub.utilities.Const
import id.ilhamsuaib.footballclub.utilities.addOnItemSelecterListener
import kotlinx.android.synthetic.main.fragment_teams.view.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by @ilhamsuaib on 27/10/18.
 * github.com/ilhamsuaib
 */

class TeamsFragment : Fragment(), ServiceCallback {

    companion object {
        fun newInstance() = TeamsFragment()
    }

    private val presenter = TeamsPresenter(Repository())
    private val teamAdapter = GroupAdapter<ViewHolder>()
    private val teamList = mutableListOf<Team>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.bindCallback(this)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_teams, container, false)
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
                getTeamsByLeague(strLeague = Const.LEAGUES[it].strLeague)
            }
        }

        view.rvTeams.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = teamAdapter
        }
    }

    private fun getTeamsByLeague(strLeague: String) {
        presenter.getTeams(strLeague)
    }

    private fun getLeagueList(): Array<String> {
        val leagues = mutableListOf<String>()
        Const.LEAGUES.forEach {
            leagues.add(it.strLeague)
        }
        return leagues.toTypedArray()
    }

    override fun showProgress(show: Boolean) {
        view?.progress?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showTeams(teamList: List<Team>) {
        this.teamList.clear()
        this.teamList.addAll(teamList)

        setAdapterItems(teamList)
    }

    private fun setAdapterItems(teamList: List<Team>) {
        teamAdapter.clear()
        teamList.forEach {
            teamAdapter.add(TeamAdapter(it) {
                startActivity<TeamDetailActivity>(Const.TEAM to it)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.menu_search)
        searchItem?.setOnActionExpandListener(actionExpandListener())

        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(queryTextListener())
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun queryTextListener(): SearchView.OnQueryTextListener? {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                filterAdapterItem(s?.toLowerCase())
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                filterAdapterItem(s?.toLowerCase())
                return false
            }
        }
    }

    private fun actionExpandListener(): MenuItem.OnActionExpandListener? {
        return object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                setAdapterItems(emptyList())
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                setAdapterItems(teamList)
                return true
            }
        }
    }

    private fun filterAdapterItem(s: String?) {
        if (s.isNullOrBlank()) {
            return
        }
        val newMatchList = teamList.filter {
            " ${it.strTeam}".toLowerCase().contains(" ${s?.toLowerCase()}") ||
                    " ${it.strTeam}".toLowerCase().contains(" ${s?.toLowerCase()}")
        }
        setAdapterItems(newMatchList)
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }
}