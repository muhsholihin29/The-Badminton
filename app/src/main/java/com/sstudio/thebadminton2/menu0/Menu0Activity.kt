package com.sstudio.thebadminton2.menu0

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.sstudio.thebadminton2.R
import com.sstudio.thebadminton2.adapter.ImageSliderAdapter
import kotlinx.android.synthetic.main.activity_menu0.*


open class Menu0Activity : AppCompatActivity() {

    private val REQUEST_PERMISSION = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu0)

        toolbar_menu0.title = "Macam-Macam Teknik Bulutangkis"
        setSupportActionBar(toolbar_menu0)
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
            R.drawable.various1,
            R.drawable.various2,
            R.drawable.various3,
            R.drawable.various4,
            R.drawable.various5,
            R.drawable.various6,
            R.drawable.various7,
            R.drawable.various8,
            R.drawable.various9)
        )

        Toast.makeText(this, "Slide >>", Toast.LENGTH_SHORT).show()
    }
}
