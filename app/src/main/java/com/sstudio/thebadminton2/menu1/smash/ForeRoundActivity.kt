package com.sstudio.thebadminton2.menu1.smash

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sstudio.thebadminton2.BuildConfig
import com.sstudio.thebadminton2.R
import kotlinx.android.synthetic.main.activity_fore_round.*
import tcking.github.com.giraffeplayer2.Option
import tcking.github.com.giraffeplayer2.PlayerManager
import tv.danmaku.ijk.media.player.IjkMediaPlayer

class ForeRoundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fore_round)

        toolbar_smash_foreround.title = getString(R.string.title_smash_forehand_dan_round_the_head_smash)
        setSupportActionBar(toolbar_smash_foreround)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        PlayerManager.getInstance().defaultVideoInfo.addOption(
            Option.create(
                IjkMediaPlayer.OPT_CATEGORY_FORMAT,
                "multiple_requests",
                1L
            )
        )

        video_view_smash_foreround.setVideoPath(BuildConfig.BASE_URL + getString(R.string.vid_smash_forehand_dan_round_the_head_smash)).player.start()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        PlayerManager.getInstance().onConfigurationChanged(newConfig)
    }
}
