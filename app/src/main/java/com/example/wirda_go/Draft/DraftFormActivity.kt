package com.example.wirda_go.Draft

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.wirda_go.BaseActivity
import com.example.wirda_go.data.AppDatabase
import com.example.wirda_go.data.entity.DraftAspirasiEntity
import com.example.wirda_go.databinding.ActivityDraftFormBinding
import com.example.wirda_go.utils.NotificationHelper
import com.example.wirda_go.utils.PermissionHelper
import com.example.wirda_go.utils.ReminderHelper
import kotlinx.coroutines.launch
import java.util.Calendar

class DraftFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDraftFormBinding
    private lateinit var db: AppDatabase
    private var kategoriId = 0

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDraftFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        db = AppDatabase.getInstance(this)
        loadSpinner()

        // Cek izin notifikasi untuk Android 13+
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            }
        }

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

                    // Tampilkan notifikasi setelah submit
                    showNotificationAfterSubmit(judul)

                    // Tampilkan reminder 1 menit
                    showReminderAfterSubmit(judul)

                    finish()
                }
            } else {
                Toast.makeText(this, "Lengkapi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showNotificationAfterSubmit(judulPengaduan: String) {
        val intent = Intent(this, BaseActivity::class.java)
        NotificationHelper.showNotification(
            this,
            "Pengaduan Terkirim",
            "Pengaduan $judulPengaduan sedang diproses oleh petugas",
            intent
        )
    }

    private fun showReminderAfterSubmit(judulPengaduan: String) {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.MINUTE, 1)
        }
        ReminderHelper.setReminder(
            context = this,
            hour = calendar.get(Calendar.HOUR_OF_DAY),
            minute = calendar.get(Calendar.MINUTE),
            title = "Cek Status Pengaduan",
            message = "Pengaduan $judulPengaduan - silakan cek statusnya",
            targetActivity = BaseActivity::class.java
        )
        Toast.makeText(
            this,
            "Reminder 1 menit akan muncul untuk cek status pengaduan",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun loadSpinner() {
        lifecycleScope.launch {
            val kategoriList = db.kategoriPengaduanDao().getAll()
            if (kategoriList.isNotEmpty()) {
                val namaList = kategoriList.map { it.namaKategori }
                val idList = kategoriList.map { it.id }
                val adapter = ArrayAdapter(
                    this@DraftFormActivity,
                    android.R.layout.simple_spinner_item,
                    namaList
                )
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

                        override fun onNothingSelected(parent: AdapterView<*>) {
                            kategoriId = 0
                        }
                    }
            } else {
                Toast.makeText(
                    this@DraftFormActivity,
                    "Belum ada kategori. Buat kategori dulu.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}