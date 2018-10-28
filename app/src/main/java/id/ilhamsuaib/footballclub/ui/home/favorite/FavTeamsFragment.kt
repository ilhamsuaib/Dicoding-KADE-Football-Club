package id.ilhamsuaib.footballclub.ui.home.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import id.ilhamsuaib.footballclub.R

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

class FavTeamsFragment : Fragment() {

    companion object {
        fun newInstance() = FavTeamsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fav_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    private fun setupView(view: View) {

    }
}