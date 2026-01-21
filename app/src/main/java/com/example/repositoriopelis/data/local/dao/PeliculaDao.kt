package com.example.repositoriopelis.data.local.dao


import androidx.room.*
import com.example.repositoriopelis.data.local.model.Pelicula
import kotlinx.coroutines.flow.Flow


@Dao
interface PeliculaDao {
    @Insert suspend fun insertar(pelicula: Pelicula)


    @Update suspend fun actualizar(pelicula: Pelicula)


    @Delete suspend fun borrar(pelicula: Pelicula)


    @Query("DELETE FROM peliculas WHERE id = :id")
    suspend fun borrarPorId(id: Int)


    @Query("SELECT * FROM peliculas ORDER BY titulo")
    fun obtenerTodas(): Flow<List<Pelicula>>


    @Query("SELECT * FROM peliculas WHERE id = :id")
    suspend fun obtenerPorId(id: Int): Pelicula?
}