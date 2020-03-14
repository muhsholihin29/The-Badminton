package com.sstudio.thebadminton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.sstudio.thebadminton.menu0.Menu0Activity
import com.sstudio.thebadminton.menu1.Menu1Activity
import com.sstudio.thebadminton.menu2.Menu2Activity
import com.sstudio.thebadminton.menu3.Menu3Activity
import kotlinx.android.synthetic.main.activity_detail_video.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.video_view


class HomeActivity : AppCompatActivity() {

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

        Common.androidId = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID)

        video_view.setVideoPath("https://sstudio-project.000webhostapp.com/video/95.mp4").player.start()
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
}
