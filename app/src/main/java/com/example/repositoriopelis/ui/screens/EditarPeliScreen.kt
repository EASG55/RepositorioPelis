package com.example.repositoriopelis.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.repositoriopelis.viewmodel.PeliculaViewModel

@Composable
fun EditarPeliScreen(id: Int, nav: NavController, vm: PeliculaViewModel) {

    val pelis by vm.peliculas.collectAsState()
    val peli = pelis.find { it.id == id }

    if (peli == null) {
        Text("Cargando...")
        return
    }

    var titulo by remember { mutableStateOf(peli.titulo) }
    var director by remember { mutableStateOf(peli.director) }
    var anio by remember { mutableStateOf(peli.anio.toString()) }
    var genero by remember { mutableStateOf(peli.genero) }

    val error = titulo.isBlank() || director.isBlank() || anio.isBlank() || genero.isBlank()

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Editar Película", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))

        TextField(titulo, { titulo = it }, label = { Text("Título") })
        TextField(director, { director = it }, label = { Text("Director") })
        TextField(anio, { anio = it }, label = { Text("Año") })
        TextField(genero, { genero = it }, label = { Text("Género") })

        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            Button(onClick = { nav.popBackStack() }) {
                Text("Cancelar")
            }

            Button(
                enabled = !error,
                onClick = {
                    vm.actualizar(id, titulo, director, anio.toInt(), genero)
                    nav.popBackStack()
                }
            ) {
                Text("Guardar cambios")
            }
        }
    }
}
