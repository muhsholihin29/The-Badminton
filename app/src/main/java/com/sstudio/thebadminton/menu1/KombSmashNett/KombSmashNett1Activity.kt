package com.sstudio.thebadminton.menu1.KombSmashNett

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import kotlinx.android.synthetic.main.activity_komb_smash_nett1.*

class KombSmashNett1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komb_smash_nett1)

        toolbar_kombsmashnett1.title = "Round The Head Smash dan Netting Forehand"
        setSupportActionBar(toolbar_kombsmashnett1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
