package id.ilhamsuaib.footballclub.ui.home.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.utilities.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.view.*

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    private fun setupView(view: View) {
        val pagerAdapter = ViewPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(FavMatchesFragment.newInstance(), getString(R.string.matches))
        pagerAdapter.addFragment(FavTeamsFragment.newInstance(), getString(R.string.teams))
        view.viewPager.adapter = pagerAdapter
        view.tabLayout.setupWithViewPager(view.viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}