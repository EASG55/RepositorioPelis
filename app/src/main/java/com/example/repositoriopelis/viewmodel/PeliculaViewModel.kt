package com.example.repositoriopelis.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repositoriopelis.data.local.model.Pelicula
import com.example.repositoriopelis.data.repository.PeliculaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class PeliculaViewModel(private val repo: PeliculaRepository) : ViewModel() {


    val peliculas = repo.obtenerTodas().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )


    fun agregar(titulo: String, director: String, anio: Int, genero: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertar(Pelicula(titulo = titulo, director = director, anio = anio, genero = genero))
        }
    }


    fun actualizar(id: Int, titulo: String, director: String, anio: Int, genero: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.actualizar(Pelicula(id, titulo, director, anio, genero))
        }
    }


    fun borrarPorId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.borrarPorId(id)
        }
    }
}