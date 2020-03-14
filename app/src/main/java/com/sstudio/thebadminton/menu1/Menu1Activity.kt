package com.sstudio.thebadminton.menu1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import com.sstudio.thebadminton.menu0.Menu0Activity
import kotlinx.android.synthetic.main.activity_home.*
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
    }
}
