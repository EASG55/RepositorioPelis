package com.example.repositoriopelis.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.repositoriopelis.ui.Screen
import com.example.repositoriopelis.viewmodel.PeliculaViewModel


@Composable
fun ListaPelisScreen(nav: NavController, vm: PeliculaViewModel) {
    val pelis by vm.peliculas.collectAsState()


    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { nav.navigate(Screen.Form.route) }) {
            Text("Añadir película")
        }
        Spacer(Modifier.height(12.dp))
        LazyColumn {
            items(pelis) { peli ->
                Card(Modifier.fillMaxWidth().padding(6.dp).clickable {
                    nav.navigate(Screen.Detalle.crear(peli.id))
                }) {
                    Column(Modifier.padding(12.dp)) {
                        Text(peli.titulo, style = MaterialTheme.typography.titleMedium)
                        Text("${peli.director} (${peli.anio})")
                    }
                }
            }
        }
    }
}