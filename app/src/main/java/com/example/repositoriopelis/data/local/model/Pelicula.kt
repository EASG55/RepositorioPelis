package com.example.repositoriopelis.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "peliculas")
data class Pelicula(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val director: String,
    val anio: Int,
    val genero: String
)