package id.ilhamsuaib.footballclub.matchDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.model.MatchDetailModel
import id.ilhamsuaib.footballclub.model.MatchModel
import id.ilhamsuaib.footballclub.model.TeamModel
import id.ilhamsuaib.footballclub.utilities.loadImage
import id.ilhamsuaib.footballclub.utilities.logD
import id.ilhamsuaib.footballclub.utilities.parseDate
import id.ilhamsuaib.footballclub.utilities.toJson
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.toast

class MatchDetailActivity : AppCompatActivity(), ServiceCallback {

    private val tag = "MatchDetailActivity"
    private val presenter = MatchDetailPresenter()
    private val detailAdapter = GroupAdapter<ViewHolder>()
    private val itemList = mutableListOf<Any>()
    private var match: MatchModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        presenter.bindCallback(this)

        initView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        this.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        rvMatchDetail.apply {
            layoutManager = LinearLayoutManager(this@MatchDetailActivity)
            adapter = detailAdapter
        }

        match = intent?.getParcelableExtra("match")

        presenter.getMatchDetail(match?.idEvent)
        presenter.getTeamDetail(match?.idHomeTeam, match?.idAwayTeam)
    }

    override fun showMatchDetail(match: MatchModel) {
        tvDateTime.text = this.match?.dateEvent?.parseDate("yyyy-MM-dd")
        tvHomeTeam.text = match.strHomeTeam
        tvAwayTeam.text = match.strAwayTeam
        tvHomeGoals.text = match.intHomeScore
        tvAwayGoals.text = match.intAwayScore

        setupDetailMatch(match)
    }

    private fun setupDetailMatch(match: MatchModel) {
        logD(tag, "setupDetailMatch : ${match.toJson()}")
        itemList.add("Match Detail")
        itemList.add(MatchDetailModel(
                leftText = match.strHomeGoalDetails,
                midText = "goals",
                rightText = match.strAwayGoalDetails
        ))
        itemList.add(MatchDetailModel(
                leftText = match.intHomeShots,
                midText = "shots",
                rightText = match.intAwayShots
        ))
        itemList.add("Lineups")
        itemList.add(MatchDetailModel(
                leftText = match.strHomeLineupGoalkeeper,
                midText = "goal keeper",
                rightText = match.strAwayLineupGoalkeeper
        ))
        itemList.add(MatchDetailModel(
                leftText = match.strHomeLineupDefense,
                midText = "defense",
                rightText = match.strAwayLineupDefense
        ))
        itemList.add(MatchDetailModel(
                leftText = match.strHomeLineupMidfield,
                midText = "midfields",
                rightText = match.strAwayLineupMidfield
        ))
        itemList.add(MatchDetailModel(
                leftText = match.strHomeLineupForward,
                midText = "forward",
                rightText = match.strAwayLineupForward
        ))
        itemList.add(MatchDetailModel(
                leftText = match.strHomeLineupSubstitutes,
                midText = "substitutes",
                rightText = match.strAwayLineupSubstitutes
        ))

        itemList.forEach {
            when (it) {
                is String -> detailAdapter.add(HeaderAdapter(it))
                is MatchDetailModel -> detailAdapter.add(ItemDetailAdapter(it))
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
}
