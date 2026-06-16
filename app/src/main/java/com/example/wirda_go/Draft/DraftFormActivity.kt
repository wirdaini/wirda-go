package com.example.wirda_go.Draft

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.wirda_go.data.AppDatabase
import com.example.wirda_go.data.entity.DraftAspirasiEntity
import com.example.wirda_go.databinding.ActivityDraftFormBinding
import kotlinx.coroutines.launch

class DraftFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDraftFormBinding
    private lateinit var db: AppDatabase
    private var kategoriId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDraftFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        db = AppDatabase.getInstance(this)
        loadSpinner()

        binding.btnSave.setOnClickListener {
            val judul = binding.etJudul.text.toString().trim()
            val isi = binding.etIsiAspirasi.text.toString().trim()
            val lokasi = binding.etLokasi.text.toString().trim()

            if (judul.isNotEmpty() && isi.isNotEmpty() && lokasi.isNotEmpty() && kategoriId != 0) {
                lifecycleScope.launch {
                    db.draftAspirasiDao().insert(
                        DraftAspirasiEntity(
                            judul = judul,
                            isiAspirasi = isi,
                            kategoriId = kategoriId,
                            lokasi = lokasi
                        )
                    )
                    Toast.makeText(this@DraftFormActivity, "Draft tersimpan", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                Toast.makeText(this, "Lengkapi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadSpinner() {
        lifecycleScope.launch {
            val kategoriList = db.kategoriPengaduanDao().getAll()
            if (kategoriList.isNotEmpty()) {
                val namaList = kategoriList.map { it.namaKategori }
                val idList = kategoriList.map { it.id }
                val adapter = ArrayAdapter(this@DraftFormActivity, android.R.layout.simple_spinner_item, namaList)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerKategori.adapter = adapter
                binding.spinnerKategori.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {

                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: android.view.View?,
                            position: Int,
                            id: Long
                        ) {
                            kategoriId = idList[position]
                        }

                        override fun onNothingSelected(
                            parent: AdapterView<*>
                        ) {
                            kategoriId = 0
                        }
                    }
            } else {
                Toast.makeText(this@DraftFormActivity, "Belum ada kategori. Buat kategori dulu.", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}