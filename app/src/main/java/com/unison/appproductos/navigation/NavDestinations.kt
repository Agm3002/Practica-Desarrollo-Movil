package com.unison.appproductos.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object ListaProductos

@Serializable
object FormularioProductos

@Serializable
data class EditarProducto(val productoId: Int)

@Serializable
object Presentacion