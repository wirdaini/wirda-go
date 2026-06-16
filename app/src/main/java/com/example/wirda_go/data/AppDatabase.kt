package com.example.wirda_go.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wirda_go.data.dao.DraftAspirasiDao
import com.example.wirda_go.data.dao.KategoriPengaduanDao
import com.example.wirda_go.data.entity.DraftAspirasiEntity
import com.example.wirda_go.data.entity.KategoriPengaduanEntity

@Database(
    entities = [KategoriPengaduanEntity::class, DraftAspirasiEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun kategoriPengaduanDao(): KategoriPengaduanDao
    abstract fun draftAspirasiDao(): DraftAspirasiDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bina_desa_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}