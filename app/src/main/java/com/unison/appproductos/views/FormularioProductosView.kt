package com.unison.appproductos.views

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.DatePicker
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import com.unison.appproductos.R
import com.unison.appproductos.dialogs.SimpleDialog
import com.unison.appproductos.model.Producto
import com.unison.appproductos.navigation.ListaProductos
import com.unison.appproductos.viewmodels.ProductoViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun FormularioProductosView(viewModel: ProductoViewModel, navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.inversePrimary)
//            .background(color = colorResource(id = R.color.azul_verde))
    ) {
        BotonAtras2(
            navController = navController,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp)
        )
        ContenidoSinNombre(navController, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoSinNombre(navController: NavHostController, viewModel: ProductoViewModel) {

    // Almacenar el valor de mis inputs aqui para validarlos en los botones
    var codigo by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
//    val datePickerState = rememberDatePickerState()
//    var selectedDate = datePickerState.selectedDateMillis?.let {
//        convertirMillisAFecha(it)
//    } ?: ""

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImgHeader()
        TextoCentrado()
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.inversePrimary)
//                .background(color = colorResource(id = R.color.verde_claro))
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
                selectedDate = selectedDate.toString(),
                onDateChange = { selectedDate = it }
            )
//            SelectorFecha(datePickerState, selectedDate)
        }
        BotonesCentrados(
            navController = navController,
            viewModel,
            nombre = nombre,
            descripcion = descripcion,
            precio = precio,
            selectedDate = selectedDate.toString()
        )
    }
}

@Composable
fun ImgHeader(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(top = 10.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.caja), contentDescription = "Imagen Caja", modifier = modifier.width(130.dp))
    }
}

@Composable
fun TextoCentrado(modifier: Modifier = Modifier) {
    Text(text = "Registro de Producto",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
//        color = colorResource(id = R.color.verde_claro),
        modifier = modifier
            .padding(10.dp)
    )
}

@Composable
fun Inputs(
    nombre: String,
    onNombreChange: (String) -> Unit,
    descripcion: String,
    onDescripcionChange: (String) -> Unit,
    precio: String,
    onPrecioChange: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
//        OutlinedTextField(
//            value = codigo,
//            onValueChange = onCodigoChange,
//            label = { Text("Código del Producto") },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = colorResource(id = R.color.verde_brillante),
//                unfocusedBorderColor = Color.Black,
//                errorBorderColor = Color.Red,
//                focusedLabelColor = colorResource(id = R.color.azul_verde)
//            ),
//            modifier = Modifier
//                .padding(start = 20.dp, end = 20.dp, top = 10.dp)
//        )
        OutlinedTextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre")},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
//                focusedBorderColor = colorResource(id = R.color.verde_brillante),
                unfocusedBorderColor = Color.Black,
                errorBorderColor = Color.Red,
                focusedLabelColor = MaterialTheme.colorScheme.primary
//                focusedLabelColor = colorResource(id = R.color.azul_verde)
            ),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
        )
        OutlinedTextField(
            value = descripcion,
            onValueChange = onDescripcionChange,
            label = { Text("Descripcion")},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
//                focusedBorderColor = colorResource(id = R.color.verde_brillante),
                unfocusedBorderColor = Color.Black,
                errorBorderColor = Color.Red,
                focusedLabelColor = MaterialTheme.colorScheme.primary
//                focusedLabelColor = colorResource(id = R.color.azul_verde)
            ),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
        )
        OutlinedTextField(
            value = precio,
            onValueChange = onPrecioChange,
            label = { Text("Precio")},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
//                focusedBorderColor = colorResource(id = R.color.verde_brillante),
                unfocusedBorderColor = Color.Black,
                errorBorderColor = Color.Red,
//                focusedLabelColor = colorResource(id = R.color.azul_verde)
                focusedLabelColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
        )
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SelectorFecha(datePickerState: DatePickerState, selectedDate: String) {
//    var showDatePicker by remember { mutableStateOf(false) }
//
//    Box(modifier = Modifier
////        .offset(x = 28.dp)
//        .width(IntrinsicSize.Min)) {
//        OutlinedTextField(
//            value = selectedDate,
//            onValueChange = {  },
//            label = { Text("Fecha de registro") },
//            readOnly = true,
//            modifier = Modifier
//                .height(64.dp)
//                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
//                .fillMaxWidth(),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = colorResource(id = R.color.verde_brillante),
//                unfocusedBorderColor = Color.Black,
//                errorBorderColor = Color.Red,
//                focusedLabelColor = colorResource(id = R.color.azul_verde)
//            ),
//            textStyle = TextStyle(Color.White)
//        )
//
//        if (showDatePicker) {
//            Popup(
//                onDismissRequest = { showDatePicker = false },
//                alignment = Alignment.TopStart
//            ) {
//                Box(
//                    modifier = Modifier
//                        .background(MaterialTheme.colorScheme.surface)
//                ) {
//                    DatePicker(
//                        state = datePickerState,
//                        showModeToggle = false,
//                    )
//                }
//            }
//        }
//    }
//}

//fun convertirMillisAFecha(millis: Long): String {
//    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//    return formatter.format(Date(millis))
//}

@Composable
fun DateInput(
    selectedDate: String,
    onDateChange: (String) -> Unit
) {
    val context = LocalContext.current

    // Initialize a Calendar instance to get the current date
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Create a DatePickerDialog
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                onDateChange("$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear")
            }, year, month, day
        )
    }

    Column {
        OutlinedButton(
            onClick = { datePickerDialog.show() },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.LightGray
            ),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 10.dp)
        ) {
            Text(text = "Fecha")
        }
//        OutlinedTextField(
//            value = selectedDate,
//            onValueChange = {  },
//            label = { Text("Fecha Seleccionada") },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = colorResource(id = R.color.verde_brillante),
//                unfocusedBorderColor = Color.Black,
//                errorBorderColor = Color.Red,
//                focusedLabelColor = colorResource(id = R.color.verde_brillante)
//            ),
//            modifier = Modifier,
////                .fillMaxWidth(),
//            readOnly = true
//        )
    }
}

@Composable
fun BotonesCentrados(
    navController: NavHostController,
    viewModel: ProductoViewModel,
    nombre: String,
    descripcion: String,
    precio: String,
    selectedDate: String
) {
    var errorMsg by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    var productoToAdd: Producto by remember { mutableStateOf(Producto()) }
    var openAddDialog by remember { mutableStateOf(false) }
    
    Column {
        Button(onClick = {
            println("Nombre: $nombre, Descripcion: $descripcion, Precio: $precio, Fecha: $selectedDate")

            try {
                if (nombre.isBlank() || descripcion.isBlank()) {
                    errorMsg = "El nombre y descripcion son requeridos"
                    showErrorDialog = true
                } else if (selectedDate.isBlank()) {
                    errorMsg = "La fecha es requerida"
                    showErrorDialog = true
                } else if (precio.toIntOrNull() == null) {
                    errorMsg = "El precio es requerido"
                    showErrorDialog = true
                } else {
                    var productoNuevo = Producto(nombre = nombre, descripcion = descripcion, precio = precio.toInt(), fecha = selectedDate)
                    productoToAdd = productoNuevo
                    openAddDialog = true
                    //viewModel.addProducto()
                    //navController.navigate(ListaProductos)
                }
            } catch (e: Exception) {
                errorMsg = "Algo salio mal: " + e.message
            }
//            val isValid = codigo.isBlank() && nombre.isBlank() && descripcion.isBlank() && precio.isBlank() && selectedDate.isBlank()
//
//            if (isValid) {
//                val producto = Producto(codigo.toInt(), nombre, descripcion, precio.toInt(), selectedDate)
//                viewModel.addProducto(producto)
//                navController.popBackStack()
//            } else {
//                showErrorDialog = true
//                errorMsg = "Todos los campos son requeridos"
//            }
        },
            colors = ButtonColors(
//                containerColor = colorResource(id = R.color.verde_brillante),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.inversePrimary,
                disabledContentColor = Color.LightGray,
                disabledContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp)
        ) {
            Text(text = "Registrar",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        }
        Alerta(
            dialogTitle = "Error",
            dialogText = errorMsg,
            onDismissRequest = {
                showErrorDialog = false
            },
            onConfirmation = {
                showErrorDialog = false
            },
            show = showErrorDialog
        )
//        Button(onClick = { navController.popBackStack() },
//            colors = ButtonColors(
//                containerColor = colorResource(id = R.color.blanco_gris),
//                contentColor = Color.Black,
//                disabledContentColor = Color.LightGray,
//                disabledContainerColor = Color.White
//            ),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 50.dp, vertical = 5.dp)
//        ) {
//            Text(text = "Cancelar",
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp
//            )
//        }
    }
    if (openAddDialog) {
        OpenAddDialog(
            onDismissRequest = {
                productoToAdd = Producto()
                openAddDialog = false
            },
            onConfirmation = {
                try {
                    viewModel.addProducto(productoToAdd)
                } catch (e: Exception) {
                    println(e)
                } finally {
                    openAddDialog = false
                    navController.navigate(ListaProductos)
                }
            }
        )
    }
}

@Composable
fun BotonAtras2(navController: NavHostController, modifier: Modifier = Modifier) {
    IconButton(
        onClick = { navController.popBackStack() },
        modifier = Modifier
            .padding(top = 22.dp, start = 10.dp)
    ) {
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = "Icono Atras",
            tint = MaterialTheme.colorScheme.primary,
//            tint = colorResource(id = R.color.verde_claro),
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun Alerta(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    show: Boolean
) {
    if (show) {
        AlertDialog(
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText)
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun OpenAddDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    SimpleDialog(
        onDismissRequest = { onDismissRequest() },
        onConfirmation = { onConfirmation() },
        dialogTitle = stringResource(id = R.string.Add),
        dialogText = stringResource(id = R.string.Action_add)
    )
}