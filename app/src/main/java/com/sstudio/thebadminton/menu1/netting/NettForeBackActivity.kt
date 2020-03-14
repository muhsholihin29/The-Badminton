package com.sstudio.thebadminton.menu1.netting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import kotlinx.android.synthetic.main.activity_nett_fore_back.*

class NettForeBackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nett_fore_back)

        toolbar_netting_foreback.title = "Netting Forehand dan Netting Backhand"
        setSupportActionBar(toolbar_netting_foreback)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
