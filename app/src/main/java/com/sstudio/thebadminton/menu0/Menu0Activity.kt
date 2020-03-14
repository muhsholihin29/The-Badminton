package com.sstudio.thebadminton.menu0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sstudio.thebadminton.R
import kotlinx.android.synthetic.main.activity_menu0.*

class Menu0Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu0)

        toolbar_menu0.title = "Macam-Macam Teknik Bulu Tangkis"
        setSupportActionBar(toolbar_menu0)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
