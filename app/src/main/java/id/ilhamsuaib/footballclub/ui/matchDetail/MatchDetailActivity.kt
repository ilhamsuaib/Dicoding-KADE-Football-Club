package id.ilhamsuaib.footballclub.ui.matchDetail

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.data.remote.model.MatchModel
import id.ilhamsuaib.footballclub.data.remote.model.TeamModel
import id.ilhamsuaib.footballclub.model.Match
import id.ilhamsuaib.footballclub.model.MatchDetail
import id.ilhamsuaib.footballclub.utilities.*
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.toast

class MatchDetailActivity : AppCompatActivity(), ServiceCallback {

    private val tag = "MatchDetailActivity"
    private val presenter = MatchDetailPresenter()
    private val detailAdapter = GroupAdapter<ViewHolder>()
    private val itemList = mutableListOf<Any>()
    private var match: Match? = null
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        presenter.bindCallback(this)

        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        match?.matchId?.let {
            presenter.checkExistenceMatch(it)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.menu_add_to_favorite -> {
                match?.let {
                    presenter.addToFavorite(it)
                }
            }
            R.id.menu_rem_from_favorite -> {
                match?.matchId?.let {
                    presenter.removeFromFavorite(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        this.title = getString(R.string.match_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        rvMatchDetail.apply {
            layoutManager = LinearLayoutManager(this@MatchDetailActivity)
            adapter = detailAdapter
        }

        match = intent?.getParcelableExtra(Const.MATCH)

        presenter.getMatchDetail(match?.matchId)
        presenter.getTeamDetail(match?.homeTeamId, match?.awayTeamId)
    }

    override fun showMatchDetail(match: MatchModel) {
        tvDateTime.text = this.match?.matchDate?.parseDate("yyyy-MM-dd")
        tvHomeTeam.text = match.strHomeTeam
        tvAwayTeam.text = match.strAwayTeam
        tvHomeGoals.text = match.intHomeScore
        tvAwayGoals.text = match.intAwayScore

        setupDetailMatch(match)
    }

    private fun setupDetailMatch(match: MatchModel) {
        logD(tag, "setupDetailMatch : ${match.toJson()}")
        itemList.add(getString(R.string.match_detail))
        itemList.add(MatchDetail(
                leftText = match.strHomeGoalDetails,
                midText = getString(R.string.goals),
                rightText = match.strAwayGoalDetails
        ))
        itemList.add(MatchDetail(
                leftText = match.intHomeShots,
                midText = getString(R.string.shots),
                rightText = match.intAwayShots
        ))
        itemList.add(getString(R.string.lineups))
        itemList.add(MatchDetail(
                leftText = match.strHomeLineupGoalkeeper,
                midText = getString(R.string.goal_keeper),
                rightText = match.strAwayLineupGoalkeeper
        ))
        itemList.add(MatchDetail(
                leftText = match.strHomeLineupDefense,
                midText = getString(R.string.defense),
                rightText = match.strAwayLineupDefense
        ))
        itemList.add(MatchDetail(
                leftText = match.strHomeLineupMidfield,
                midText = getString(R.string.midfields),
                rightText = match.strAwayLineupMidfield
        ))
        itemList.add(MatchDetail(
                leftText = match.strHomeLineupForward,
                midText = getString(R.string.forward),
                rightText = match.strAwayLineupForward
        ))
        itemList.add(MatchDetail(
                leftText = match.strHomeLineupSubstitutes,
                midText = getString(R.string.substitutes),
                rightText = match.strAwayLineupSubstitutes
        ))

        itemList.forEach {
            when (it) {
                is String -> detailAdapter.add(HeaderAdapter(it))
                is MatchDetail -> detailAdapter.add(ItemDetailAdapter(it))
            }
        }
    }

    override fun onFailed(message: String) {
        toast(message)
    }

    override fun showHomeTeam(team: TeamModel) {
        logD(tag, "showHomeTeam : ${team.toJson()}")
        imgHomeTeam.loadImage(team.strTeamBadge)
    }

    override fun showAwayTeam(team: TeamModel) {
        imgAwayTeam.loadImage(team.strTeamBadge)
    }

    override fun savedToFavorite() {
        menu?.clear()
        menuInflater.inflate(R.menu.menu_rem_fav, menu)
        Snackbar.make(container, getString(R.string.ditambahkan_ke_favorit), Snackbar.LENGTH_SHORT).show()
    }

    override fun removedToFavorite() {
        menu?.clear()
        menuInflater.inflate(R.menu.menu_add_fav, menu)
        Snackbar.make(container, getString(R.string.dihapus_dari_favorit), Snackbar.LENGTH_SHORT).show()
    }

    override fun savedAsFavorite(saved: Boolean) {
        val resMenu = if (saved) R.menu.menu_rem_fav else R.menu.menu_add_fav
        menuInflater.inflate(resMenu, menu)
    }
}
