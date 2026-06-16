package com.example.wirda_go.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.wirda_go.data.entity.KategoriPengaduanEntity

@Dao
interface KategoriPengaduanDao {

    @Query("SELECT * FROM kategori_pengaduan ORDER BY createdAt DESC")
    suspend fun getAll(): List<KategoriPengaduanEntity>

    @Insert
    suspend fun insert(kategori: KategoriPengaduanEntity)

    @Delete
    suspend fun delete(kategori: KategoriPengaduanEntity)
}