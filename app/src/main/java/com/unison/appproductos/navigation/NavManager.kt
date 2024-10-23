package com.unison.appproductos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.unison.appproductos.views.FormularioProductosView
import com.unison.appproductos.views.PresentacionView
import com.unison.appproductos.views.HomeView
import com.unison.appproductos.views.ListaProductosView
import com.unison.appproductos.viewmodels.ProductoViewModel
import com.unison.appproductos.views.EditarProductoView

@Composable
fun NavManager(viewModel: ProductoViewModel, modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeView(navController)
        }
        composable<ListaProductos> {
            ListaProductosView(viewModel, navController)
        }
        composable<FormularioProductos> {
            FormularioProductosView(viewModel, navController)
        }
        composable<EditarProducto> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<EditarProducto>()
            EditarProductoView(args.productoId, viewModel, navController)
        }
        composable<Presentacion> {
            PresentacionView(navController)
        }
    }
}