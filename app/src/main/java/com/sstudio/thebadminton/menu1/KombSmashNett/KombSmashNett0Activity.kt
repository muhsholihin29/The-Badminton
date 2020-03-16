package com.sstudio.thebadminton.menu1.KombSmashNett

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import kotlinx.android.synthetic.main.activity_komb_smash_nett0.*
import tcking.github.com.giraffeplayer2.PlayerManager

class KombSmashNett0Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komb_smash_nett0)

        toolbar_kombsmashnett0.title = getString(R.string.title_Smash_Forehand_dan_Netting_Backhand)
        setSupportActionBar(toolbar_kombsmashnett0)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        video_view_kombsmashnett0.setVideoPath(getString(R.string.vid_Smash_Forehand_dan_Netting_Backhand)).player.start()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        PlayerManager.getInstance().onConfigurationChanged(newConfig)
    }
}
