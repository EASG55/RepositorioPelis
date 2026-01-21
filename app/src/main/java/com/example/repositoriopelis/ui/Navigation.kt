package com.example.repositoriopelis.ui


sealed class Screen(val route: String) {
    object Lista : Screen("lista")
    object Form : Screen("form")
    object Editar : Screen("editar/{id}") {
        fun crear(id: Int) = "editar/$id"
    }
    object Detalle : Screen("detalle/{id}") {
        fun crear(id: Int) = "detalle/$id"
    }
}