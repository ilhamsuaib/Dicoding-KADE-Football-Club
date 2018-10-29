package id.ilhamsuaib.footballclub.ui.home.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.ui.home.teams.TeamAdapter
import id.ilhamsuaib.footballclub.model.Team
import id.ilhamsuaib.footballclub.ui.teamDetail.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_fav_teams.view.*
import org.jetbrains.anko.support.v4.startActivity
import id.ilhamsuaib.footballclub.utilities.Const

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

class FavTeamsFragment : Fragment(), ServiceCallback {

    companion object {
        fun newInstance() = FavTeamsFragment()
    }

    private val presenter = FavoritePresenter()
    private val favTeamAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.bindCallback(this)
        return inflater.inflate(R.layout.fragment_fav_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        presenter.getFavoriteTeams()
    }

    private fun setupView(view: View) {
        view.rvFavTeams.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favTeamAdapter
        }
    }

    override fun showFavoriteTeams(teamList: List<Team>) {
        teamList.forEach {
            favTeamAdapter.add(TeamAdapter(it) {
                startActivity<TeamDetailActivity>(Const.TEAM to it)
            })
        }
    }
}