package com.sstudio.thebadminton2.menu1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sstudio.thebadminton2.R
import com.sstudio.thebadminton2.menu1.KombSmashNett.*
import kotlinx.android.synthetic.main.activity_komb_smash_nett.*
import com.sstudio.thebadminton2.menu1.KombSmashNett.KombSmashNett6Activity as KombSmashNett6Activity1

class KombSmashNettActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komb_smash_nett)

        toolbar_kombsmashnett.title = "Kombinasi Variasi Smash dan Netting"
        setSupportActionBar(toolbar_kombsmashnett)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        menu_kombsmashnett_0.setOnClickListener {
            startActivity(Intent(this, KombSmashNett0Activity::class.java))
        }

        menu_kombsmashnett_1.setOnClickListener {
            startActivity(Intent(this, KombSmashNett1Activity::class.java))
        }

        menu_kombsmashnett_2.setOnClickListener {
            startActivity(Intent(this, KombSmashNett2Activity::class.java))
        }

        menu_kombsmashnett_3.setOnClickListener {
            startActivity(Intent(this, KombSmashNett3Activity::class.java))
        }

        menu_kombsmashnett_4.setOnClickListener {
            startActivity(Intent(this, KombSmashNett4Activity::class.java))
        }

        menu_kombsmashnett_5.setOnClickListener {
            startActivity(Intent(this, KombSmashNett5Activity::class.java))
        }

        menu_kombsmashnett_6.setOnClickListener {
            startActivity(Intent(this, KombSmashNett6Activity1::class.java))
        }

    }
}
