package com.sstudio.thebadminton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_coba2.*

class CobaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coba2)

        video_view.setVideoPath("https://sstudio-project.000webhostapp.com/video/95.mp4").player.start()
    }
}
