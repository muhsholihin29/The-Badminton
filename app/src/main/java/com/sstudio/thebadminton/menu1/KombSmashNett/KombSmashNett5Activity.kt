package com.sstudio.thebadminton.menu1.KombSmashNett

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import kotlinx.android.synthetic.main.activity_komb_smash_nett5.*

class KombSmashNett5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komb_smash_nett5)

        toolbar_kombsmashnett5.title = "Round The Head Smash dan Netting Backhand Berpasangan"
        setSupportActionBar(toolbar_kombsmashnett5)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
