package com.sstudio.thebadminton2.menu2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.sstudio.thebadminton2.R
import com.sstudio.thebadminton2.adapter.ImageSliderAdapter
import kotlinx.android.synthetic.main.activity_menu2.*

class Menu2Activity : AppCompatActivity() {

    private val REQUEST_PERMISSION = 110

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu2)

        toolbar_menu2.title = "Peraturan Permainan Bulutangkis"
        setSupportActionBar(toolbar_menu2)
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
            R.drawable.rules1,
            R.drawable.rules2,
            R.drawable.rules3,
            R.drawable.rules4,
            R.drawable.rules5,
            R.drawable.rules6)
        )

        Toast.makeText(this, "Slide >>", Toast.LENGTH_SHORT).show()
    }
}
