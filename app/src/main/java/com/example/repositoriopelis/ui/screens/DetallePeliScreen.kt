package com.example.repositoriopelis.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.repositoriopelis.ui.Screen
import com.example.repositoriopelis.viewmodel.PeliculaViewModel


@Composable
fun DetallePeliScreen(id: Int, nav: NavController, vm: PeliculaViewModel) {
    val pelis by vm.peliculas.collectAsState()
    val peli = pelis.find { it.id == id }


    Column(
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        peli?.let {
            Text(it.titulo, style = MaterialTheme.typography.headlineMedium)
            Text("Director: ${it.director}")
            Text("Año: ${it.anio}")
            Text("Género: ${it.genero}")
        }
        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { nav.navigate(Screen.Editar.crear(id)) }) { Text("Editar") }
            Button(onClick = {
                vm.borrarPorId(id)
                nav.popBackStack()
            }) { Text("Borrar") }
            Button(onClick = { nav.popBackStack() }) { Text("Volver") }
        }
    }
}