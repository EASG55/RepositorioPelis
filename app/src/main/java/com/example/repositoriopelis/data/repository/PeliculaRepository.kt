package com.example.repositoriopelis.data.repository

import com.example.repositoriopelis.data.local.dao.PeliculaDao
import com.example.repositoriopelis.data.local.model.Pelicula


class PeliculaRepository(private val dao: PeliculaDao) {
    fun obtenerTodas() = dao.obtenerTodas()
    suspend fun insertar(pelicula: Pelicula) = dao.insertar(pelicula)
    suspend fun actualizar(pelicula: Pelicula) = dao.actualizar(pelicula)
    suspend fun borrar(pelicula: Pelicula) = dao.borrar(pelicula)
    suspend fun borrarPorId(id: Int) = dao.borrarPorId(id)
    suspend fun obtenerPorId(id: Int) = dao.obtenerPorId(id)
}