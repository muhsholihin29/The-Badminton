package com.sstudio.thebadminton2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.splash_screen.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        var isCoach = false

        tv_coach.setOnClickListener {
            isCoach = true
            startActivity(Intent(this, CoachActivity::class.java))
            finish()
        }

        Handler().postDelayed({
            if (!isCoach) {
                val intent = Intent(this@SplashScreen, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}
