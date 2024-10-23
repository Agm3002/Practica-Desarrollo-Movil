package com.unison.appproductos.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unison.appproductos.model.Producto
import com.unison.appproductos.room.ProductsDatabaseDao
import com.unison.appproductos.state.ProductoState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductoViewModel(
    private val dao: ProductsDatabaseDao
) : ViewModel() {

    var state by mutableStateOf(ProductoState())
        private set

    init {
        viewModelScope.launch {

            // Obtener la lista de productos en la base de datos
            dao.getProducts().collectLatest {

                // Guardar en el estado la lista de productos
                state = state.copy (
                    productoList = it
                )
            }
        }
    }

    fun getProductoById(codigo: Int): Producto? {
        return state.productoList.find {
            codigo == it.codigo
        }
    }

    fun addProducto(producto: Producto) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.addProduct(producto)
        }
    }

    fun updateProducto(producto: Producto) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.updateProduct(producto)
        }
    }

    fun deleteProducto(producto: Producto) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteProduct(producto)
        }
    }
}

//class ProductoViewModel : ViewModel() {
//
//    // Estado del modelo.
//    var estado by mutableStateOf(ProductoState())
//        private set
//
//    // Inicializar del view model.
//    init {
//        viewModelScope.launch {
//
//            // Espera 2 segundos.
//            delay(2000)
//
//            // Cargar los datos.
//            estado = estado.copy(
//                productos = listOf(
//                    Producto("Coca-Cola 2L", "Coca-Cola 2L Original", "11/09/2024", 35),
//                    Producto("Paleta Payaso", "Chocolate y gomitas", "11/09/2024",20)
//                ),
//                estaCargando = false
//            )
//        }
//    }
//
//}