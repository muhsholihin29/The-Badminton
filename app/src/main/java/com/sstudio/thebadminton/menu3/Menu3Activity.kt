package com.sstudio.thebadminton.menu3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import com.sstudio.thebadminton.menu3.mvp.note.CatatanActivity
import kotlinx.android.synthetic.main.activity_menu3.*

class Menu3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu3)

        toolbar_menu3.title = "Catatan"
        setSupportActionBar(toolbar_menu3)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        menu_catatan_catatanlatihhan.setOnClickListener {
            startActivity(Intent(this, CatatanActivity::class.java))
        }

        menu_catatan_video.setOnClickListener {
            startActivity(Intent(this, VideoActivity::class.java))
        }
    }
}
