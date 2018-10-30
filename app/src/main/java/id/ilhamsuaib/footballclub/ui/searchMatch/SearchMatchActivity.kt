package id.ilhamsuaib.footballclub.ui.searchMatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.data.Repository
import id.ilhamsuaib.footballclub.model.Match
import id.ilhamsuaib.footballclub.ui.home.matches.MatchAdapter
import id.ilhamsuaib.footballclub.ui.matchDetail.MatchDetailActivity
import id.ilhamsuaib.footballclub.utilities.Const
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by @ilhamsuaib on 30/10/18.
 * github.com/ilhamsuaib
 */

class SearchMatchActivity : AppCompatActivity(), ServiceCallback {

    private val presenter = SearchMatchPresenter(Repository())
    private val matchAdapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.bindCallback(this)
        setContentView(R.layout.activity_search_match)

        setupView()
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.title = ""

        rvSearchMatch.apply {
            layoutManager = LinearLayoutManager(this@SearchMatchActivity)
            adapter = matchAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.menu_search)
        searchItem?.expandActionView()
        searchItem?.setOnActionExpandListener(actionExpandListener())

        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(queryTextListener())
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun actionExpandListener(): MenuItem.OnActionExpandListener? {
        return object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {

                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                finish()
                return true
            }
        }
    }

    private fun queryTextListener(): SearchView.OnQueryTextListener? {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                presenter.searchMatch(s)
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                presenter.searchMatch(s)
                return false
            }
        }
    }

    override fun showMatchResults(matchList: List<Match>) {
        matchAdapter.clear()
        matchList.forEach {
            matchAdapter.add(MatchAdapter(it, false) {
                startActivity<MatchDetailActivity>(Const.MATCH to it)
            })
        }
    }

    override fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }
}