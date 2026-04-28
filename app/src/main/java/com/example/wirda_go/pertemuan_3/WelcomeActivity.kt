package com.example.wirda_go.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wirda_go.databinding.ActivityWelcomeBinding
import com.example.wirda_go.pertemuan_2.BangunKalkulatorActivity
import com.example.wirda_go.pertemuan_4.JobBoardActivity
import com.example.wirda_go.pertemuan_4.PortofolioActivity
import com.example.wirda_go.pertemuan_6.WebViewActivity
import com.example.wirda_go.pertemuan_6.SplashScreenActivity


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

        // ===== 4 TOMBOL ====
        // Tombol 1: Rumus Bangun Ruang
        binding.btnRumusBangunRuang.setOnClickListener {
            val intent = Intent(this, BangunKalkulatorActivity::class.java)
            intent.putExtra("EXTRA_JUDUL", "Rumus Bangun Ruang")
            intent.putExtra("EXTRA_DESKRIPSI", "Hitung volume dan luas permukaan bangun ruang dengan mudah")
            startActivity(intent)
        }

        // Tombol 2: Job Board
        binding.btnJobBoard.setOnClickListener {
            val intent = Intent(this, JobBoardActivity::class.java)
            intent.putExtra("EXTRA_JUDUL", "Job Board")
            intent.putExtra("EXTRA_DESKRIPSI", "Temukan proyek freelance terbaik dari seluruh dunia")
            startActivity(intent)
        }

        // Tombol 3: Portofolio
        binding.btnPortofolio.setOnClickListener {
            val intent = Intent(this, PortofolioActivity::class.java)
            intent.putExtra("EXTRA_JUDUL", "Portofolio Saya")
            intent.putExtra("EXTRA_DESKRIPSI", "Bangun portofolio luar biasa untuk menarik klien")
            startActivity(intent)
        }

        // ===== TOMBOL BARU: Web View / Desktop View Bina Desa =====
        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            // Optional: kirim data judul dan deskripsi (kalo mau ditampilkan di toolbar WebView)
            intent.putExtra("EXTRA_JUDUL", "Website Bina Desa")
            intent.putExtra("EXTRA_DESKRIPSI", "Lihat informasi lengkap tentang Bina Desa")
            startActivity(intent)
        }

        // Tombol 4: Logout dengan Konfirmasi
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ ->

                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    // Pindah ke LoginActivity
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    Log.e("Info Dialog","Anda memilih Ya!")
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { _, _ ->
                    Toast.makeText(this, "Logout dibatalkan", Toast.LENGTH_SHORT).show()
                    Log.e("Info Dialog","Anda memilih Tidak!")
                }
                .show()
        }
    }
}