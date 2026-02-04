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
fun EditarPeliScreen(
    id: Int,
    nav: NavController,
    vm: PeliculaViewModel
) {

    val peliculas by vm.peliculas.collectAsState()
    val pelicula = peliculas.find { it.id == id }

    // Seguridad extra: evita crash si aún no está cargada
    if (pelicula == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    var titulo by remember { mutableStateOf(pelicula.titulo) }
    var director by remember { mutableStateOf(pelicula.director) }
    var anio by remember { mutableStateOf(pelicula.anio.toString()) }
    var genero by remember { mutableStateOf(pelicula.genero) }

    var anioError by remember { mutableStateOf<String?>(null) }
    var guardando by remember { mutableStateOf(false) }

    fun validarAnio(): Boolean {
        return try {
            anio.toInt()
            anioError = null
            true
        } catch (e: NumberFormatException) {
            anioError = "Introduce un año válido"
            false
        }
    }

    val camposInvalidos =
        titulo.isBlank() || director.isBlank() || anio.isBlank() || genero.isBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Editar película",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(12.dp))

        TextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") }
        )

        TextField(
            value = director,
            onValueChange = { director = it },
            label = { Text("Director") }
        )

        TextField(
            value = anio,
            onValueChange = {
                anio = it
                anioError = null
            },
            label = { Text("Año") },
            isError = anioError != null,
            supportingText = {
                anioError?.let { Text(it) }
            }
        )

        TextField(
            value = genero,
            onValueChange = { genero = it },
            label = { Text("Género") }
        )

        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(
                onClick = { nav.popBackStack() }
            ) {
                Text("Cancelar")
            }

            Button(
                enabled = !camposInvalidos && !guardando,
                onClick = {
                    if (!validarAnio()) return@Button
                    guardando = true
                    vm.actualizar(
                        id = id,
                        titulo = titulo,
                        director = director,
                        anio = anio.toInt(),
                        genero = genero
                    )
                    nav.popBackStack()
                }
            ) {
                Text("Guardar cambios")
            }
        }
    }
}
