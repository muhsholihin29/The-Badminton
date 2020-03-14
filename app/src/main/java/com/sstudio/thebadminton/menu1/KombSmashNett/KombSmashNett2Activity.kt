package com.sstudio.thebadminton.menu1.KombSmashNett

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import kotlinx.android.synthetic.main.activity_komb_smash_nett2.*

class KombSmashNett2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komb_smash_nett2)

        toolbar_kombsmashnett2.title = "Netting Forehand dan Netting Backhand Berpasanagan"
        setSupportActionBar(toolbar_kombsmashnett2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
