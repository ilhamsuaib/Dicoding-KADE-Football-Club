package id.ilhamsuaib.footballclub.ui.playerDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import id.ilhamsuaib.footballclub.R
import id.ilhamsuaib.footballclub.model.Player
import id.ilhamsuaib.footballclub.utilities.Const
import id.ilhamsuaib.footballclub.utilities.loadImage
import kotlinx.android.synthetic.main.activity_player_detail.*

/**
 * Created by @ilhamsuaib on 28/10/18.
 * github.com/ilhamsuaib
 */

class PlayerDetailActivity : AppCompatActivity() {

    private var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        player = intent?.getParcelableExtra(Const.PLAYER)

        setupView()
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.title = player?.strPlayer

        imgBanner.loadImage(player?.strFanart1)
        tvHeight.text = player?.strHeight
        tvHeight.text = player?.strHeight
        tvWeight.text = player?.strWeight
        tvPosition.text = player?.strPosition
        tvPlayerDescription.text = player?.strDescriptionEN
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}