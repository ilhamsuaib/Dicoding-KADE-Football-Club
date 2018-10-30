package id.ilhamsuaib.footballclub.ui.home.matches

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
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
import id.ilhamsuaib.footballclub.utilities.getTimeMillis
import kotlinx.android.synthetic.main.fragment_next_match.view.*
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
    private val matchList = mutableListOf<Match>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.bindCallback(this)
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)
    }

    private fun setupView(view: View) {
        val spinnerAdapter = ArrayAdapter<String>(view.context, android.R.layout.simple_spinner_dropdown_item, getLeagueList())
        view.spLeagueNextMatch.apply {
            adapter = spinnerAdapter
            addOnItemSelectedListener {
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
        this.matchList.clear()
        this.matchList.addAll(matchList)
        setAdapterItems(matchList)
    }

    private fun setAdapterItems(matchList: List<Match>) {
        matchAdapter.clear()
        matchList.forEach {
            matchAdapter.add(MatchAdapter(match = it, isAlarm = true,
                    addToCalendar = {
                        addToCalendar(it)
                    }, onItemClick = {
                startActivity<MatchDetailActivity>(Const.MATCH to it)
            }
            ))
        }
    }

    private fun addToCalendar(match: Match) {
        val dateTime = "${match.matchDate} ${match.strTime}"
        val intent = Intent(Intent.ACTION_EDIT).apply {
            type = "vnd.android.cursor.item/event"
            putExtra(CalendarContract.Events.TITLE, "${match.homeTeamName} vs ${match.awayTeamName}")
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, dateTime.getTimeMillis())
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, dateTime.getTimeMillis().plus(3600000))
            putExtra(CalendarContract.Events.ALL_DAY, false)
        }
        startActivity(intent)
    }

    override fun showProgress(show: Boolean) {
        view?.progress?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }
}