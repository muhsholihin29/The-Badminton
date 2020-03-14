package com.sstudio.thebadminton.menu1.KombSmashNett

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import kotlinx.android.synthetic.main.activity_komb_smash_nett0.*

class KombSmashNett0Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komb_smash_nett0)

        toolbar_kombsmashnett0.title = "Smash Forehand dan Netting Backhand"
        setSupportActionBar(toolbar_kombsmashnett0)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
