package com.unison.appproductos.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.unison.appproductos.navigation.ListaProductos
import com.unison.appproductos.navigation.Presentacion
import com.unison.appproductos.R

@Composable
fun HomeView(navController: NavHostController) {
    Home(navController)
}

@Composable
fun Home(navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            //.background(color = colorResource(id = R.color.azul_verde))
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Imagen()
        Botones(navController)
        MiNombre()
    }
}

@Composable
fun Imagen(modifier: Modifier = Modifier) {
    Image(painter = painterResource(id = R.drawable.agmproductslogo), contentDescription = "AgmProductsLogo", modifier = modifier.width(550.dp))
}

@Composable
fun Botones(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExtendedFloatingActionButton(
            onClick = { navController.navigate(ListaProductos) },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.inversePrimary
//            containerColor = colorResource(id = R.color.verde_claro),
//            contentColor = colorResource(id = R.color.verde_brillante)
        ) {
            Text(text = "Productos", fontSize = 25.sp)
        }

        ExtendedFloatingActionButton(
            onClick = { navController.navigate(Presentacion) },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.inversePrimary,
            //containerColor = colorResource(id = R.color.verde_claro),
            //contentColor = colorResource(id = R.color.verde_brillante),
            modifier = modifier.padding(top = 20.dp)
        ) {
            Text(text = "Presentación", fontSize = 25.sp)
        }
    }
}

@Composable
fun MiNombre(modifier: Modifier = Modifier) {
    Text(
        text = "Armando González Martínez",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
//        color = colorResource(id = R.color.verde_claro),
        modifier = modifier.padding(bottom = 50.dp)
    )
}