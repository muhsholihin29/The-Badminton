package com.sstudio.thebadminton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_coach.*

class CoachActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coach)
        btn_login.setOnClickListener {
            when {
                tv_name_coach.text.toString() == "" -> Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
                tv_password.text.toString() == Common.password -> {
                    Common.isCoach = true
                    Common.coach = tv_name_coach.text.toString()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                else -> Toast.makeText(this, "Password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
