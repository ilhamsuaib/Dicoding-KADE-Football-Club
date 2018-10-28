package id.ilhamsuaib.footballclub.ui.teamDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ilhamsuaib.footballclub.R
import kotlinx.android.synthetic.main.fragment_team_overview.view.*

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

class TeamOverviewFragment : Fragment() {

    companion object {
        private const val CLUB_DESCRIPTION = "description"
        fun newInstance(description: String?): TeamOverviewFragment {
            var fragment: TeamOverviewFragment? = null
            if (fragment == null)
                fragment = TeamOverviewFragment()
            val args = Bundle()
            args.putString(CLUB_DESCRIPTION, description)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val description = arguments?.getString(CLUB_DESCRIPTION)
        view.tvOverview.text = description
    }
}