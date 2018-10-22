package id.ilhamsuaib.footballclub.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.ui.home.favorite.FavoritesFragment
import id.ilhamsuaib.footballclub.ui.home.match.LastMatchFragment
import id.ilhamsuaib.footballclub.ui.home.match.NextMatchFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    companion object {
        private const val MATCH_TYPE = "matchType"
        const val LEAGUE_ID = "4335"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
    }

    private fun initView() {
        this.title = getString(R.string.laliga_santander)
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(LastMatchFragment(), getString(R.string.last_match))
        pagerAdapter.addFragment(NextMatchFragment(), getString(R.string.next_match))
        pagerAdapter.addFragment(FavoritesFragment(), getString(R.string.favorites))
        viewPager.adapter = pagerAdapter
        tabView.setupWithViewPager(viewPager)
    }
}
