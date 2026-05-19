package com.example.wirda_go.Home.pertemuan_9

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wirda_go.R
import com.example.wirda_go.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        enableEdgeToEdge()
        setContentView(R.layout.activity_ninth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnKirimPengaduan.setOnClickListener {
            val judul = binding.etJudul.text.toString().trim()
            val deskripsi = binding.etDeskripsi.text.toString().trim()
            val lokasi = binding.etLokasi.text.toString().trim()

            var isValid = true

            // Validasi judul
            if (judul.isEmpty()) {
                binding.tilJudul.error = "Judul pengaduan wajib diisi!"
                isValid = false
            } else {
                binding.tilJudul.error = null // Hapus pesan error kalau sudah benar
            }

            // Validasi deskripsi
            if (deskripsi.isEmpty()) {
                binding.tilDeskripsi.error = "Deskripsi wajib diisi!"
                isValid = false
            } else {
                binding.tilDeskripsi.error = null
            }

            // Kalau semua valid
            if (isValid) {
                Toast.makeText(this, "Pengaduan berhasil dikirim!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.chipGroupKategori.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()

            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                // Tampilkan pesan kategori yang dipilih
                Toast.makeText(
                    this,
                    "Filter: ${chip.text}",
                    Toast.LENGTH_SHORT
                ).show()

                // Di sini kamu bisa tambahkan logika filter data
                // Contoh: filterPengaduan(chip.text.toString())
            }
        }
    }

    // Ini yang membuat tombol panah back di toolbar berfungsi
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}

