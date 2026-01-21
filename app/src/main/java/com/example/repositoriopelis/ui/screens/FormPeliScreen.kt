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
fun FormPeliScreen(nav: NavController, vm: PeliculaViewModel) {
    var titulo by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }


    val error = titulo.isBlank() || director.isBlank() || anio.isBlank() || genero.isBlank()


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Nueva Película", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))


        TextField(titulo, { titulo = it }, label = { Text("Título") })
        TextField(director, { director = it }, label = { Text("Director") })
        TextField(anio, { anio = it }, label = { Text("Año") })
        TextField(genero, { genero = it }, label = { Text("Género") })


        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { nav.popBackStack() }) { Text("Volver") }
            Button(enabled = !error, onClick = {
                vm.agregar(titulo, director, anio.toInt(), genero)
                nav.popBackStack()
            }) { Text("Guardar") }
        }
    }
}