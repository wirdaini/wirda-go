package com.example.wirda_go.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kategori_pengaduan")
data class KategoriPengaduanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaKategori: String,
    val deskripsi: String,
    val createdAt: Long = System.currentTimeMillis()
)