package com.unison.appproductos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.unison.appproductos.navigation.NavManager
import com.unison.appproductos.room.ProductoDatabase
import com.unison.appproductos.ui.theme.AppProductosTheme
import com.unison.appproductos.viewmodels.ProductoViewModel

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = ProductoDatabase::class.java,
            name = "productos.db"
        )
    }

    // Método que es llamado al crear la activity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Instancia del viewModel.
        val viewModel: ProductoViewModel = ProductoViewModel(db.build().productosDao())

        // Establecer el contenido de la aplicación.
        setContent {
            AppProductosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavManager(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}