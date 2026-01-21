package com.example.repositoriopelis.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.repositoriopelis.data.local.dao.PeliculaDao
import com.example.repositoriopelis.data.local.model.Pelicula


@Database(entities = [Pelicula::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun peliculaDao(): PeliculaDao


    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pelis_db"
                ).build().also { instance = it }
            }
    }
}