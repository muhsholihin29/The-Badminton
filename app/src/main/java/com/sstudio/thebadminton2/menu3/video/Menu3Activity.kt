package com.sstudio.thebadminton2.menu3.video

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sstudio.thebadminton2.R
import com.sstudio.thebadminton2.menu3.note.NoteActivity
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
            startActivity(Intent(this, NoteActivity::class.java))
        }

        menu_catatan_video.setOnClickListener {
            startActivity(Intent(this, VideoActivity::class.java))
        }
    }
}
