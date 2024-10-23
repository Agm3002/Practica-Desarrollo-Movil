package com.unison.appproductos.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavHostController
import com.unison.appproductos.navigation.FormularioProductos
import com.unison.appproductos.R
import com.unison.appproductos.dialogs.SimpleDialog
import com.unison.appproductos.model.Producto
import com.unison.appproductos.navigation.EditarProducto
import com.unison.appproductos.navigation.ListaProductos
import com.unison.appproductos.viewmodels.ProductoViewModel

@Composable
fun ListaProductosView(viewModel: ProductoViewModel, navController: NavHostController) {
    Scaffold(
        floatingActionButton = {
            BotonAgregar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
//                .background(color = colorResource(id = R.color.verde_claro))
                .background(color = MaterialTheme.colorScheme.inversePrimary)
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            BotonAtras(
                navController = navController,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(16.dp)
            )
            // Titulo.
            Text(
                text = "Productos",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
//                color = colorResource(id = R.color.azul_verde),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 30.dp)
            )

            // Estado del viewModel.
            val state = viewModel.state

            var productoToDelete: Producto by remember { mutableStateOf(Producto()) }
            var openDeleteDialog by remember { mutableStateOf(false) }


            // Carga.
            if (state.productoList.isEmpty()) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(id = R.string.no_products_available))
                }
            } else {
                // Mostrar los productos.
                LazyColumn {

                    // Definición de los registros.
                    items(state.productoList) {
                        ProductoItemCard(
                            producto = it,
                            navController = navController,
                            onEditClick = {
                                navController.navigate(
                                    EditarProducto(it.codigo)
                                )
                            },
                            onDeleteClick = {
//                                viewModel.deleteProducto(it)
                                openDeleteDialog = true
                                productoToDelete = it
                            }
                        )
                        HorizontalDivider()
                    }
                }
                if (openDeleteDialog) {
                    OpenDeleteDialog(
                        onDismissRequest = {
                            openDeleteDialog = false
                            productoToDelete = Producto()
                        },
                        onConfirmation = {
                            try {
                                viewModel.deleteProducto(productoToDelete)
                            } catch (e: Exception) {
                                println(e)
                            } finally {
                                openDeleteDialog = false
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductoItemCard(
    producto: Producto,
    navController: NavHostController,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color.White)
            .clickable(onClick = { onEditClick() }), // Permite hacer clic para editar
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
//        backgroundColor = Color.White,
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                text = "Precio: \$${producto.precio}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    onEditClick()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit",
                        tint = Color.Black
                    )
                }
                IconButton(onClick = {
                    onDeleteClick()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun BotonAgregar(navController: NavHostController, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { navController.navigate(FormularioProductos) },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.inversePrimary
//        containerColor = colorResource(id = R.color.azul_verde),
//        contentColor = colorResource(id = R.color.verde_claro)
    ) {
        // Icono para el botón de agregar
        Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Producto")
    }
}

@Composable
fun BotonAtras(navController: NavHostController, modifier: Modifier = Modifier) {
    IconButton(
        onClick = { navController.popBackStack() },
        modifier = Modifier
            .padding(top = 22.dp, start = 10.dp)
    ) {
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = "Icono Atras",
            tint = MaterialTheme.colorScheme.primary,
//            tint = colorResource(id = R.color.azul_verde),
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun OpenDeleteDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
   SimpleDialog(
       onDismissRequest = { onDismissRequest() },
       onConfirmation = { onConfirmation() },
       dialogTitle = stringResource(id = R.string.Delete),
       dialogText = stringResource(id = R.string.Action_Undone)
   )
}