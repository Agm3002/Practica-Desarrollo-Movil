package com.unison.appproductos.state

import com.unison.appproductos.model.Producto

data class ProductoState(
    val productoList: List<Producto> = emptyList()
)

//data class ProductoState(
//    val productos: List<Producto> = listOf(),
//    val estaCargando: Boolean = true
//)