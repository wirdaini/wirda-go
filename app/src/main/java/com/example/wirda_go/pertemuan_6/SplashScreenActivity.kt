package com.example.wirda_go.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.wirda_go.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        Handler(Looper.getMainLooper()).postDelayed({

            if (isLogin) {
                startActivity(Intent(this, com.example.wirda_go.pertemuan_3.WelcomeActivity::class.java))
            } else {
                startActivity(Intent(this, com.example.wirda_go.pertemuan_3.LoginActivity::class.java))
            }

            finish()
        }, 2000)
    }
}