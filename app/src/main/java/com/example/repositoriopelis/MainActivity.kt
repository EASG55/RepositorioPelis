package com.example.repositoriopelis


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.repositoriopelis.data.local.database.AppDatabase
import com.example.repositoriopelis.data.repository.PeliculaRepository
import com.example.repositoriopelis.ui.Screen
import com.example.repositoriopelis.ui.screens.*
import com.example.repositoriopelis.viewmodel.PeliculaViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val dao = AppDatabase.getInstance(this).peliculaDao()
        val repo = PeliculaRepository(dao)
        val vm = PeliculaViewModel(repo)


        setContent { App(vm) }
    }
}


@Composable
fun App(vm: PeliculaViewModel) {
    val nav = rememberNavController()
    NavHost(nav, startDestination = Screen.Lista.route) {
        composable(Screen.Lista.route) { ListaPelisScreen(nav, vm) }
        composable(Screen.Form.route) { FormPeliScreen(nav, vm) }
        composable(Screen.Detalle.route) { back ->
            val id = back.arguments?.getString("id")!!.toInt()
            DetallePeliScreen(id, nav, vm)
        }
        composable(Screen.Editar.route) { back ->
            val id = back.arguments?.getString("id")!!.toInt()
            EditarPeliScreen(id, nav, vm)
        }

    }
}