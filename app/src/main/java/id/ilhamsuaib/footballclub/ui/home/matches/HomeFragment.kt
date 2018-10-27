package id.ilhamsuaib.footballclub.ui.home.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.ui.home.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Created by @ilhamsuaib on 27/10/18.
 * github.com/ilhamsuaib
 */

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = ViewPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(LastMatchFragment(), getString(R.string.last))
        pagerAdapter.addFragment(NextMatchFragment(), getString(R.string.next))
        view.viewPager.adapter = pagerAdapter
        view.tabView.setupWithViewPager(view.viewPager)
    }
}