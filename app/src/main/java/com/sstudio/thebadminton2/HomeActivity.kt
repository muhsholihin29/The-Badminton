package com.sstudio.thebadminton2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sstudio.thebadminton2.menu0.Menu0Activity
import com.sstudio.thebadminton2.menu1.Menu1Activity
import com.sstudio.thebadminton2.menu2.Menu2Activity
import com.sstudio.thebadminton2.menu3.video.Menu3Activity
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    private val REQUEST_PERMISSION = 110
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar_home.title = "The Badminton"
        setSupportActionBar(toolbar_home)

        menu_home_0.setOnClickListener {
            startActivity(Intent(this, Menu0Activity::class.java))
        }

        menu_home_1.setOnClickListener {
            startActivity(Intent(this, Menu1Activity::class.java))
        }

        menu_home_2.setOnClickListener {
            startActivity(Intent(this, Menu2Activity::class.java))
        }

        menu_home_3.setOnClickListener {
            startActivity(Intent(this, Menu3Activity::class.java))
        }

        if (Common.isCoach){
            Toast.makeText(this, "Anda masuk sebagai pelatih", Toast.LENGTH_SHORT).show()

        }
        Common.androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        permissionRead()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about) {
            val mIntent = Intent(this, AboutActivity::class.java)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // Permission granted.
            } else { // User refused to grant permission.
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_PERMISSION
                )
            }
        }
    }

    private fun permissionRead(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) !== PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION
            )
            return
        }
    }
}
