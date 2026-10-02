package com.cristo.tempconvert.ViewsUI

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
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TempCalculatorPage(viewModel: ViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()

    when (state.currentScreen) {
        AppScreen.WELCOME -> {
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
                        fontSize = 15.sp,
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
        }

        AppScreen.MENU -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF263238))
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.menu_title),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(40.dp))

                    Button(
                        onClick = { viewModel.navigateTo(AppScreen.TEMPERATURE) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
                        modifier = Modifier.fillMaxWidth().height(56.dp)
                    ) {
                        Text(text = stringResource(id = R.string.menu_btn_temp), fontSize = 16.sp, color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { viewModel.navigateTo(AppScreen.LENGTH) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00ACC1)),
                        modifier = Modifier.fillMaxWidth().height(56.dp)
                    ) {
                        Text(text = stringResource(id = R.string.menu_btn_length), fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }

        AppScreen.TEMPERATURE -> {
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

                OutlinedTextField(
                    value = state.celsius,
                    onValueChange = { viewModel.onCelsiusChanged(it) },
                    label = { Text(stringResource(id = R.string.label_celsius)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = state.fahrenheit,
                    onValueChange = { viewModel.onFahrenheitChanged(it) },
                    label = { Text(stringResource(id = R.string.label_fahrenheit)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = state.kelvin,
                    onValueChange = { viewModel.onKelvinChanged(it) },
                    label = { Text(stringResource(id = R.string.label_kelvin)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(32.dp))
                TextButton(onClick = { viewModel.navigateTo(AppScreen.MENU) }) {
                    Text(text = stringResource(id = R.string.btn_back), color = Color(0xFFFF7043), fontSize = 16.sp)
                }
            }
        }

        AppScreen.LENGTH -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF263238))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.length_conversor_title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = state.meters,
                    onValueChange = { viewModel.onMetersChanged(it) },
                    label = { Text(stringResource(id = R.string.label_meters)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.kilometers,
                    onValueChange = { viewModel.onKilometersChanged(it) },
                    label = { Text(stringResource(id = R.string.label_kilometers)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.centimeters,
                    onValueChange = { viewModel.onCentimetersChanged(it) },
                    label = { Text(stringResource(id = R.string.label_centimeters)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.feet,
                    onValueChange = { viewModel.onFeetChanged(it) },
                    label = { Text(stringResource(id = R.string.label_feet)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.miles,
                    onValueChange = { viewModel.onMilesChanged(it) },
                    label = { Text(stringResource(id = R.string.label_miles)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))
                TextButton(onClick = { viewModel.navigateTo(AppScreen.MENU) }) {
                    Text(text = stringResource(id = R.string.btn_back), color = Color(0xFF00ACC1), fontSize = 16.sp)
                }
            }
        }
    }
}