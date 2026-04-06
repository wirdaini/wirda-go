package com.example.wirda_go.pertemuan_3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wirda_go.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Kita ambil data "USER_NAME" yang dikirim tadi
        val namaDariLogin = intent.getStringExtra("USER_NAME")

        // Tampilkan ke TextView
        if (!namaDariLogin.isNullOrEmpty()) {
            binding.tvWelcome.text = "Selamat Datang, $namaDariLogin!"
        } else {
            binding.tvWelcome.text = "Selamat Datang, User!"
        }
    }
}