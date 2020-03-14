package com.sstudio.thebadminton.menu1.smash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import kotlinx.android.synthetic.main.activity_fore_round.*

class ForeRoundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fore_round)

        toolbar_smash_foreround.title = "Smash Forehand dan Round The Head Smash"
        setSupportActionBar(toolbar_smash_foreround)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
