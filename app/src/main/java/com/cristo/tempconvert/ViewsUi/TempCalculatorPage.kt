package com.cristo.tempconvert.ViewsUi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cristo.tempconvert.R
import com.cristo.tempconvert.ViewsUI.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TempCalculatorPage(viewModel: ViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()

    if (state.showWelcome) {
        // Pantalla de Bienvenida
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFB3E5FC), Color(0xFFFFCC80))
                    )
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.welcome_title),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.welcome_subtitle),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { viewModel.enterApp() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043))
                ) {
                    Text(text = stringResource(id = R.string.btn_start), color = Color.White)
                }
            }
        }
    } else {
        // Pantalla de la Calculadora Bidireccional (Sin botón de limpiar)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF263238))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.conversor_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Campo Celsius
            OutlinedTextField(
                value = state.celsius,
                onValueChange = { viewModel.onCelsiusChanged(it) },
                label = { Text(stringResource(id = R.string.label_celsius)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo Fahrenheit
            OutlinedTextField(
                value = state.fahrenheit,
                onValueChange = { viewModel.onFahrenheitChanged(it) },
                label = { Text(stringResource(id = R.string.label_fahrenheit)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo Kelvin
            OutlinedTextField(
                value = state.kelvin,
                onValueChange = { viewModel.onKelvinChanged(it) },
                label = { Text(stringResource(id = R.string.label_kelvin)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }
    }
}