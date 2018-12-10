package id.ilhamsuaib.footballclub.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.ui.home.favorite.FavMatchesFragment
import id.ilhamsuaib.footballclub.ui.home.favorite.FavoriteFragment
import id.ilhamsuaib.footballclub.ui.home.matches.HomeFragment
import id.ilhamsuaib.footballclub.ui.home.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    companion object {
        var idlingResourceCounter = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
    }

    private fun initView() {
        setupFragment(HomeFragment.newInstance())

        bottomNav.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.menu_matches -> setupFragment(HomeFragment.newInstance())
                R.id.menu_teams -> setupFragment(TeamsFragment.newInstance())
                R.id.menu_favorite -> setupFragment(FavoriteFragment.newInstance())
                else -> false
            }
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }*/

    private fun setupFragment(fragment: Fragment): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        return true
    }
}
