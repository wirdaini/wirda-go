package com.example.wirda_go.pertemuan_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wirda_go.R
import com.example.wirda_go.databinding.ActivityJobBoardBinding

class JobBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari Intent (dikirim dari WelcomeActivity)
        val judul = intent.getStringExtra("EXTRA_JUDUL") ?: "Job Board"
        val deskripsi = intent.getStringExtra("EXTRA_DESKRIPSI") ?: "Temukan proyek freelance terbaik dari seluruh dunia"

        // Tampilkan ke TextView menggunakan binding
        binding.tvJudulJob.text = judul
        binding.tvDeskripsiJob.text = deskripsi
    }
}