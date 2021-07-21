package com.sstudio.thebadminton2.menu1.KombSmashNett

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sstudio.thebadminton2.BuildConfig
import com.sstudio.thebadminton2.R
import kotlinx.android.synthetic.main.activity_komb_smash_nett6.*
import tcking.github.com.giraffeplayer2.Option
import tcking.github.com.giraffeplayer2.PlayerManager
import tv.danmaku.ijk.media.player.IjkMediaPlayer

class KombSmashNett6Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komb_smash_nett6)

        toolbar_kombsmashnett6.title = getString(R.string.title_Round_The_Head_Smash_dan_Netting_Forehand_Berpasangan)
        setSupportActionBar(toolbar_kombsmashnett6)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        PlayerManager.getInstance().defaultVideoInfo.addOption(
            Option.create(
                IjkMediaPlayer.OPT_CATEGORY_FORMAT,
                "multiple_requests",
                1L
            )
        )

        video_view_kombsmashnett6.setVideoPath(BuildConfig.BASE_URL +getString(R.string.vid_Round_The_Head_Smash_dan_Netting_Forehand_Berpasangan)).player.start()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        PlayerManager.getInstance().onConfigurationChanged(newConfig)
    }
}
