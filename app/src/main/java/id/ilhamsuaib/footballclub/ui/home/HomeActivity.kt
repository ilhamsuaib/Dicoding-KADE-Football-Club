package id.ilhamsuaib.footballclub.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.ui.home.favorite.FavoritesFragment
import id.ilhamsuaib.footballclub.ui.home.match.MatchFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
    }

    private fun initView() {
        this.title = getString(R.string.laliga_santander)
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(MatchFragment.newInstance("eventspastleague.php"), getString(R.string.last_match))
        pagerAdapter.addFragment(MatchFragment.newInstance("eventsnextleague.php"), getString(R.string.next_match))
        pagerAdapter.addFragment(FavoritesFragment.newInstance(), getString(R.string.favorites))
        viewPager.adapter = pagerAdapter
        tabView.setupWithViewPager(viewPager)
    }
}
