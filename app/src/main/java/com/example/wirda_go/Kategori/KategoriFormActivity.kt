package com.example.wirda_go.Kategori

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.wirda_go.data.AppDatabase
import com.example.wirda_go.data.entity.KategoriPengaduanEntity
import com.example.wirda_go.databinding.ActivityKategoriFormBinding
import kotlinx.coroutines.launch

class KategoriFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKategoriFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoriFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        db = AppDatabase.getInstance(this)

        binding.btnSave.setOnClickListener {
            val nama = binding.etNamaKategori.text.toString().trim()
            val desc = binding.etDeskripsi.text.toString().trim()

            if (nama.isNotEmpty() && desc.isNotEmpty()) {
                lifecycleScope.launch {
                    db.kategoriPengaduanDao().insert(
                        KategoriPengaduanEntity(namaKategori = nama, deskripsi = desc)
                    )
                    Toast.makeText(this@KategoriFormActivity, "Kategori tersimpan", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                Toast.makeText(this, "Lengkapi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}