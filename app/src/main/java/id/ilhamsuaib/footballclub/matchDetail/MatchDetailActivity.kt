package id.ilhamsuaib.footballclub.matchDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.data.model.MatchModel
import id.ilhamsuaib.footballclub.data.model.TeamModel
import id.ilhamsuaib.footballclub.utilities.loadImage
import id.ilhamsuaib.footballclub.utilities.logD
import id.ilhamsuaib.footballclub.utilities.parseDate
import id.ilhamsuaib.footballclub.utilities.toJson
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.toast

class MatchDetailActivity : AppCompatActivity(), ServiceCallback {

    private val tag = "MatchDetailActivity"
    private val presenter = MatchDetailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        presenter.bindCallback(this)

        val match: MatchModel? = intent?.getParcelableExtra("match")
        Log.d(tag, "onCreate : ${match.toJson()}")
        presenter.getMatchDetail(match?.idEvent)
        presenter.getTeamDetail(match?.idHomeTeam, match?.idAwayTeam)
    }

    override fun showMatchDetail(match: MatchModel) {
        tvDateTime.text = match.dateEvent?.parseDate("yyyy-mm-dd")
        tvHomeTeam.text = match.strHomeTeam
        tvAwayTeam.text = match.strAwayTeam
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
