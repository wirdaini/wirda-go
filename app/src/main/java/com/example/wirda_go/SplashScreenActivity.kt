package com.example.wirda_go

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.wirda_go.onboarding.OnboardingActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val userPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = userPref.getBoolean("isLogin", false)

        Handler(Looper.getMainLooper()).postDelayed({

            if (isLogin) {
                // Sudah login → langsung masuk app
                startActivity(Intent(this, BaseActivity::class.java))
            } else {
                // Belum login → onboarding dulu SELALU
                startActivity(Intent(this, OnboardingActivity::class.java))
            }

            finish()
        }, 2000)
    }
}