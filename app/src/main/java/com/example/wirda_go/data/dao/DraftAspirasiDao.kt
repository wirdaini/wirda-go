package com.example.wirda_go.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.wirda_go.data.entity.DraftAspirasiEntity

@Dao
interface DraftAspirasiDao {

    @Query("SELECT * FROM draft_aspirasi ORDER BY createdAt DESC")
    suspend fun getAll(): List<DraftAspirasiEntity>

    @Insert
    suspend fun insert(draft: DraftAspirasiEntity)

    @Delete
    suspend fun delete(draft: DraftAspirasiEntity)
}