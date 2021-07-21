package com.sstudio.thebadminton2.menu1.netting

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sstudio.thebadminton2.BuildConfig
import com.sstudio.thebadminton2.R
import kotlinx.android.synthetic.main.activity_nett_backhand.*
import tcking.github.com.giraffeplayer2.Option
import tcking.github.com.giraffeplayer2.PlayerManager
import tv.danmaku.ijk.media.player.IjkMediaPlayer

class NettBackhandActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nett_backhand)

        toolbar_netting_back.title = getString(R.string.title_netting_backhand)
        setSupportActionBar(toolbar_netting_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        PlayerManager.getInstance().defaultVideoInfo.addOption(
            Option.create(
                IjkMediaPlayer.OPT_CATEGORY_FORMAT,
                "multiple_requests",
                1L
            )
        )

        video_view_netting_back.setVideoPath(BuildConfig.BASE_URL +getString(R.string.vid_netting_backhand)).player.start()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        PlayerManager.getInstance().onConfigurationChanged(newConfig)
    }
}
