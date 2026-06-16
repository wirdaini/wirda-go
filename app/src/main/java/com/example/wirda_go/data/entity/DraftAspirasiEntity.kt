package com.example.wirda_go.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "draft_aspirasi")
data class DraftAspirasiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val isiAspirasi: String,
    val kategoriId: Int,
    val lokasi: String,
    val createdAt: Long = System.currentTimeMillis()
)