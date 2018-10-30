package id.ilhamsuaib.footballclub.ui.home.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.ui.searchMatch.SearchMatchActivity
import id.ilhamsuaib.footballclub.utilities.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by @ilhamsuaib on 27/10/18.
 * github.com/ilhamsuaib
 */

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = ViewPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(NextMatchFragment(), getString(R.string.next))
        pagerAdapter.addFragment(LastMatchFragment(), getString(R.string.last))
        view.homeViewPager.adapter = pagerAdapter
        view.tabView.setupWithViewPager(view.homeViewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search2, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_search)
            startActivity<SearchMatchActivity>()
        return super.onOptionsItemSelected(item)
    }
}