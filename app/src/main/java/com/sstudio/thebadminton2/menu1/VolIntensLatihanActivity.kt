package com.sstudio.thebadminton2.menu1

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.sstudio.thebadminton2.R
import com.sstudio.thebadminton2.adapter.ImageSliderAdapter
import kotlinx.android.synthetic.main.activity_vol_intens_latihan.*

class VolIntensLatihanActivity : AppCompatActivity() {

    private val REQUEST_PERMISSION = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vol_intens_latihan)

        toolbar_vol_intens.title = "Macam-Macam Teknik Bulutangkis"
        setSupportActionBar(toolbar_vol_intens)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION
            )
        }
        view_pager.adapter = ImageSliderAdapter(intArrayOf(
            R.drawable.vol_intens)
        )
    }
}