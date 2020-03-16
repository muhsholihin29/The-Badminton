package com.sstudio.thebadminton.menu1.KombSmashNett

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import kotlinx.android.synthetic.main.activity_komb_smash_nett1.*
import tcking.github.com.giraffeplayer2.PlayerManager

class KombSmashNett1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komb_smash_nett1)

        toolbar_kombsmashnett1.title = "Round The Head Smash dan Netting Forehand"
        setSupportActionBar(toolbar_kombsmashnett1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        video_view_kombsmashnett1.setVideoPath("https://sstudio-project.000webhostapp.com/video/95.mp4").player.start()
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        PlayerManager.getInstance().onConfigurationChanged(newConfig)
    }
}
