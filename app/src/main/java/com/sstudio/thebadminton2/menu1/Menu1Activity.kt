package com.sstudio.thebadminton2.menu1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sstudio.thebadminton2.R
import kotlinx.android.synthetic.main.activity_menu1.*

class Menu1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu1)

        toolbar_menu1.title = "Variasi Smash dan Netting"
        setSupportActionBar(toolbar_menu1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        menu_varsmashnett_smash.setOnClickListener {
            startActivity(Intent(this, VarSmashActivity::class.java))
        }

        menu_varsmashnett_netting.setOnClickListener {
            startActivity(Intent(this, VarNettActivity::class.java))
        }

        menu_varsmashnett_kombinasi.setOnClickListener {
            startActivity(Intent(this, KombSmashNettActivity::class.java))
        }

        menu_vol_intens_latihan.setOnClickListener {
            startActivity(Intent(this, VolIntensLatihanActivity::class.java))
        }
    }
}
