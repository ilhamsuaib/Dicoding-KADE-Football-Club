package id.ilhamsuaib.footballclub.ui.teamDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.data.Repository
import id.ilhamsuaib.footballclub.model.Player
import id.ilhamsuaib.footballclub.ui.playerDetail.PlayerDetailActivity
import id.ilhamsuaib.footballclub.utilities.Const
import kotlinx.android.synthetic.main.fragment_team_players.view.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

class TeamPlayersFragment : Fragment(), ServiceCallback {

    companion object {
        private const val CLUB_ID = "club_id"
        fun newInstance(teamId: String?): TeamPlayersFragment {
            var fragment: TeamPlayersFragment? = null
            if (fragment == null)
                fragment = TeamPlayersFragment()
            val args = Bundle()
            args.putString(CLUB_ID, teamId)
            fragment.arguments = args
            return fragment
        }
    }

    private val presenter = TeamPresenter(Repository())
    private val playerAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.bindCallback(this)
        return inflater.inflate(R.layout.fragment_team_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        val idTeam = arguments?.getString(CLUB_ID)
        presenter.getTeamPlayers(idTeam)
    }

    private fun setupView(view: View) {
        view.rvPlayers.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = playerAdapter
        }
    }

    override fun showPlayers(playerList: List<Player>) {
        playerAdapter.clear()
        playerList.forEach {
            playerAdapter.add(PlayerAdapter(it) {
                startActivity<PlayerDetailActivity>(Const.PLAYER to it)
            })
        }
    }

    override fun showProgress(show: Boolean) {
        view?.progress?.visibility = if (show) View.VISIBLE else View.GONE
    }
}