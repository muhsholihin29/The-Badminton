package com.sstudio.thebadminton.menu1.netting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import kotlinx.android.synthetic.main.activity_nett_backhand.*

class NettBackhandActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nett_backhand)

        toolbar_netting_back.title = "Netting Backhand"
        setSupportActionBar(toolbar_netting_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
