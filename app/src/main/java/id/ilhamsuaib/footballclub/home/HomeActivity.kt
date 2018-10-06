package id.ilhamsuaib.footballclub.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.ilhamsuaib.footballclub.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
    }

    private fun initView() {
        this.title = "La Liga Santander"
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(MatchFragment.newInstance("eventspastleague.php"), "Last Match")
        pagerAdapter.addFragment(MatchFragment.newInstance("eventsnextleague.php"), "Next Match")
        viewPager.adapter = pagerAdapter
        tabView.setupWithViewPager(viewPager)
    }
}
