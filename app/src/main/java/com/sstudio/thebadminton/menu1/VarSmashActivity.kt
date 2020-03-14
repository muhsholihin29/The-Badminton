package com.sstudio.thebadminton.menu1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import com.sstudio.thebadminton.menu1.smash.ForeRoundActivity
import com.sstudio.thebadminton.menu1.smash.ForehandActivity
import com.sstudio.thebadminton.menu1.smash.RoundHeadActivity
import kotlinx.android.synthetic.main.activity_var_smash.*

class VarSmashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_var_smash)

        toolbar_varsmash.title = "Variasi Smash"
        setSupportActionBar(toolbar_varsmash)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        menu_varsmash_fore.setOnClickListener {
            startActivity(Intent(this, ForehandActivity::class.java))
        }

        menu_varsmash_round.setOnClickListener {
            startActivity(Intent(this, RoundHeadActivity::class.java))
        }

        menu_varsmash_foreround.setOnClickListener {
            startActivity(Intent(this, ForeRoundActivity::class.java))
        }
    }
}
