package com.unison.appproductos.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.unison.appproductos.R

@Composable
fun PresentacionView(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
//            .background(colorResource(id = R.color.azul_verde))
    ) {
        Atras(
            navController = navController,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Presentacion()
        }
        Datos(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun Atras(navController: NavHostController, modifier: Modifier = Modifier) {
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
fun Presentacion(modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.profile), contentDescription = "Foto de Perfil", modifier = modifier.width(160.dp))
        Text(text = "Armando González Martínez",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
//            color = colorResource(id = R.color.verde_claro),
            modifier = modifier
                .padding(0.dp, 3.dp)
        )
        Text(text = "Desarrollador Web",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
//            color = colorResource(id = R.color.verde_claro)
        )
    }
}

@Composable
fun Datos(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
            .padding(bottom = 50.dp)
//            .background(color = colorResource(id = R.color.AzulOscuro))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(painter = painterResource(id = R.drawable.call), contentDescription = "Icono Telefono",modifier.width(15.dp))
            Text(text = "637 129 5088",
//                color = colorResource(id = R.color.verde_claro),
                modifier.padding(5.dp, 0.dp))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(painter = painterResource(id = R.drawable.logo_instagram), contentDescription = "Logo Instagram", modifier.width(15.dp))
            Text(text = "@armandoglz_30",
//                color = colorResource(id = R.color.white),
                modifier.padding(5.dp, 3.dp))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(painter = painterResource(id = R.drawable.mail), contentDescription = "Icono Correo", modifier.width(15.dp))
            Text(text = "a220200635@unison.mx",
//                color = colorResource(id = R.color.white),
                modifier.padding(5.dp, 0.dp))
        }
    }
}