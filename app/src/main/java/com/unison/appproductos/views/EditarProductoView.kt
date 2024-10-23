package com.unison.appproductos.views

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.unison.appproductos.navigation.Home
import com.unison.appproductos.navigation.ListaProductos
import com.unison.appproductos.R
import com.unison.appproductos.dialogs.SimpleDialog
import com.unison.appproductos.model.Producto
import com.unison.appproductos.viewmodels.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarProductoView(productoId: Int, viewModel: ProductoViewModel, navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
//                    containerColor = colorResource(id = R.color.azul_verde),
                    titleContentColor = MaterialTheme.colorScheme.inversePrimary
                ),
                title = {
                    Text(text = "Editar Producto", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineLarge)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ListaProductos)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = MaterialTheme.colorScheme.inversePrimary
//                            tint = colorResource(id = R.color.verde_claro)
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = MaterialTheme.colorScheme.inversePrimary))
//                .background(color = colorResource(id = R.color.azul_verde)))
        {
            FormularioEditar(producto = viewModel.getProductoById(productoId), viewModel, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioEditar(producto: Producto?, viewModel: ProductoViewModel, navController: NavHostController, modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf(producto?.nombre ?: "") }
    var precio by remember { mutableStateOf(producto?.precio.toString()) }
    var descripcion by remember { mutableStateOf(producto?.descripcion ?: "") }
    var selectedDate by remember { mutableStateOf(producto?.fecha ?: "") }

    // Para el DatePicker
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, year, month, day
    )

    var errorMsg by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    var openUpdateDialog by remember { mutableStateOf(false) }
    var productoToUpdate: Producto by remember { mutableStateOf(Producto()) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.inversePrimary)
//            .background(color = colorResource(id = R.color.verde_claro))
    ) {
        Inputs(
            nombre = nombre,
            onNombreChange = { nombre = it },
            descripcion = descripcion,
            onDescripcionChange = { descripcion = it },
            precio = precio,
            onPrecioChange = { precio = it }
        )
        DateInput(
            selectedDate = selectedDate,
            onDateChange = { selectedDate = it }
        )
        Button(onClick = {
            try {
                if (nombre.isBlank() || descripcion.isBlank() || selectedDate.isBlank()) {
                    errorMsg = "El nombre, descripción y fecha son requeridos"
                    showErrorDialog = true
                } else if (precio.toIntOrNull() == null) {
                    errorMsg = "El precio tiene que ser un entero válido"
                    showErrorDialog = true
                } else {
                    var productoModificado = Producto(codigo = producto?.codigo!!, nombre = nombre, descripcion = descripcion, precio = precio.toInt(), fecha = selectedDate)
                    productoToUpdate = productoModificado
                    openUpdateDialog = true
                }
            } catch (e: Exception) {
                errorMsg = "Algo salió terriblemente mal"
            }
        }, modifier = modifier,
            shape = RoundedCornerShape(4.dp),
            contentPadding = PaddingValues(40.dp, 18.dp),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.inversePrimary,
                disabledContainerColor = colorResource(id = R.color.verde_brillante),
                disabledContentColor = Color.White))
        {
            Text(text = "Actualizar", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
    if (openUpdateDialog) {
        OpenUpdateDialog(
            onDismissRequest = {
                productoToUpdate = Producto()
                openUpdateDialog = false
            },
            onConfirmation = {
                try {
                    viewModel.updateProducto(productoToUpdate)
                } catch (e: Exception) {
                    println(e)
                } finally {
                    openUpdateDialog = false
                    navController.navigate(ListaProductos)
                }
            }
        )
    }
}

@Composable
fun OpenUpdateDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    SimpleDialog(
        onDismissRequest = { onDismissRequest() },
        onConfirmation = { onConfirmation() },
        dialogTitle = stringResource(id = R.string.Update),
        dialogText = stringResource(id = R.string.Action_update)
    )
}