package com.sstudio.thebadminton.menu1.netting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import kotlinx.android.synthetic.main.activity_nett_forehand.*

class NettForehandActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nett_forehand)

        toolbar_netting_fore.title = getString(R.string.title_netting_forehand)
        setSupportActionBar(toolbar_netting_fore)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
