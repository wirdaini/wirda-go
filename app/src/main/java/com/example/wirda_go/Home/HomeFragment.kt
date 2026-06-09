package com.example.wirda_go.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wirda_go.AuthActivity
import com.example.wirda_go.Data.Api.NewsApiClient
import com.example.wirda_go.Home.pertemuan_2.BangunKalkulatorActivity
import com.example.wirda_go.Home.pertemuan_4.JobBoardActivity
import com.example.wirda_go.Home.pertemuan_4.PortofolioActivity
import com.example.wirda_go.Home.pertemuan_6.WebViewActivity
import com.example.wirda_go.Home.pertemuan_7.SeventhActivity
import com.example.wirda_go.Home.pertemuan_9.NinthActivity
import com.example.wirda_go.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        // ===== 4 TOMBOL ====
        // Tombol 1: Rumus Bangun Ruang
        binding.btnRumusBangunRuang.setOnClickListener {
            val intent = Intent(requireContext(), BangunKalkulatorActivity::class.java)
            intent.putExtra("EXTRA_JUDUL", "Rumus Bangun Ruang")
            intent.putExtra("EXTRA_DESKRIPSI", "Hitung volume dan luas permukaan bangun ruang dengan mudah")
            startActivity(intent)
        }

        // Tombol 2: Job Board
        binding.btnJobBoard.setOnClickListener {
            val intent = Intent(requireContext(), JobBoardActivity::class.java)
            intent.putExtra("EXTRA_JUDUL", "Job Board")
            intent.putExtra("EXTRA_DESKRIPSI", "Temukan proyek freelance terbaik dari seluruh dunia")
            startActivity(intent)
        }

        // Tombol 3: Portofolio
        binding.btnPortofolio.setOnClickListener {
            val intent = Intent(requireContext(), PortofolioActivity::class.java)
            intent.putExtra("EXTRA_JUDUL", "Portofolio Saya")
            intent.putExtra("EXTRA_DESKRIPSI", "Bangun portofolio luar biasa untuk menarik klien")
            startActivity(intent)
        }

        // ===== TOMBOL BARU: Web View / Desktop View Bina Desa =====
        binding.btnWebView.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            // Optional: kirim data judul dan deskripsi (kalo mau ditampilkan di toolbar WebView)
            intent.putExtra("EXTRA_JUDUL", "Website Bina Desa")
            intent.putExtra("EXTRA_DESKRIPSI", "Lihat informasi lengkap tentang Bina Desa")
            startActivity(intent)
        }

        // Tombol Pertemuan 7
        binding.btnSeventh.setOnClickListener {
            val intent = Intent(requireContext(), SeventhActivity::class.java)
            intent.putExtra("EXTRA_JUDUL", "Pertemuan 7")
            intent.putExtra("EXTRA_DESKRIPSI", "Semanagat sampai tamat!!!")
            startActivity(intent)
        }

        // Tombol Pertemuan 9
        binding.btnNinth.setOnClickListener {
            val intent = Intent(requireContext(), NinthActivity::class.java)
            startActivity(intent)
        }

        // Tombol 4: Logout dengan Konfirmasi
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ ->

                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    // Pindah ke OnboardingActivity
                    val intent = Intent(requireContext(), com.example.wirda_go.onboarding.OnboardingActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    Log.e("Info Dialog","Anda memilih Ya!")
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak") { _, _ ->
                    Toast.makeText(requireContext(), "Logout dibatalkan", Toast.LENGTH_SHORT).show()
                    Log.e("Info Dialog","Anda memilih Tidak!")
                }
                .show()
        }

        loadNews()
    }

    private fun loadNews() {
        lifecycleScope.launch {
            try {
                val response = NewsApiClient.apiService.getNews()
                val adapter = NewsAdapter(response.articles)
                binding.rvNews.adapter = adapter
                binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
            } catch (e: Exception) {
                Log.e("NewsError", "Error: ${e.message}")
                Log.e("NewsError", "Cause: ${e.cause}")
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}