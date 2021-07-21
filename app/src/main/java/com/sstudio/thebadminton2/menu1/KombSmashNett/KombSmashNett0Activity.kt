package com.sstudio.thebadminton2.menu1.KombSmashNett

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sstudio.thebadminton2.BuildConfig.BASE_URL
import com.sstudio.thebadminton2.R
import kotlinx.android.synthetic.main.activity_komb_smash_nett0.*
import tcking.github.com.giraffeplayer2.Option
import tcking.github.com.giraffeplayer2.PlayerManager
import tv.danmaku.ijk.media.player.IjkMediaPlayer

class KombSmashNett0Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komb_smash_nett0)

        toolbar_kombsmashnett0.title = getString(R.string.title_Smash_Forehand_dan_Netting_Backhand)
        setSupportActionBar(toolbar_kombsmashnett0)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        PlayerManager.getInstance().defaultVideoInfo.addOption(
            Option.create(
                IjkMediaPlayer.OPT_CATEGORY_FORMAT,
                "multiple_requests",
                1L
            )
        )

        video_view_kombsmashnett0.setVideoPath(BASE_URL+getString(R.string.vid_Smash_Forehand_dan_Netting_Backhand)).player.start()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        PlayerManager.getInstance().onConfigurationChanged(newConfig)
    }
}
