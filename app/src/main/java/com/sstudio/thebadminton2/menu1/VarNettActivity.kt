package com.sstudio.thebadminton2.menu1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sstudio.thebadminton2.R
import com.sstudio.thebadminton2.menu1.netting.NettBackhandActivity
import com.sstudio.thebadminton2.menu1.netting.NettForeBackActivity
import com.sstudio.thebadminton2.menu1.netting.NettForehandActivity
import kotlinx.android.synthetic.main.activity_var_nett.*

class VarNettActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_var_nett)

        toolbar_varnett.title = "Variasi Netting"
        setSupportActionBar(toolbar_varnett)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        menu_varnetting_fore.setOnClickListener {
            startActivity(Intent(this, NettForehandActivity::class.java))
        }

        menu_varnetting_back.setOnClickListener {
            startActivity(Intent(this, NettBackhandActivity::class.java))
        }

        menu_varnetting_foreback.setOnClickListener {
            startActivity(Intent(this, NettForeBackActivity::class.java))
        }
    }
}
