package com.example.wirda_go.Home.pertemuan_4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wirda_go.R
import com.example.wirda_go.databinding.ActivityPortofolioBinding

class PortofolioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPortofolioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPortofolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari Intent (dikirim dari WelcomeActivity)
        val judul = intent.getStringExtra("EXTRA_JUDUL") ?: "Portofolio Saya"
        val deskripsi = intent.getStringExtra("EXTRA_DESKRIPSI") ?: "Bangun portofolio luar biasa untuk menarik klien"

        // Tampilkan ke TextView menggunakan binding
        binding.tvJudulPortofolio.text = judul
        binding.tvDeskripsiPortofolio.text = deskripsi

        // Tombol Tambah Portofolio
        binding.btnTambahPortofolio.setOnClickListener {
            Toast.makeText(this, "Fitur tambah portofolio akan segera hadir!", Toast.LENGTH_SHORT).show()
        }
    }
}