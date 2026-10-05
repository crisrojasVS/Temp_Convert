package com.cristo.tempconvert.ViewsUI

import android.provider.ContactsContract
import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cristo.tempconvert.R
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TempCalculatorPage(viewModel: ViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()

    when (state.currentScreen) {
        AppScreen.WELCOME -> WelcomeScreen(viewModel)
        AppScreen.MENU -> MenuScreen(viewModel)
        AppScreen.TEMPERATURE -> TemperatureScreen(viewModel, state)
        AppScreen.LENGTH -> LengthScreen(viewModel, state)
        AppScreen.TIME -> TimeScreen(viewModel, state)
    }
}

// Apartado de Bienvenida
@Composable
fun WelcomeScreen(viewModel: ViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            shape = RoundedCornerShape(13.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B).copy(alpha = 0.9f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier.size(72.dp),
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xFFFF7043).copy(alpha = 0.2f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Calculate,
                            contentDescription = null,
                            tint = Color(0xFFFF7043),
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(id = R.string.welcome_title),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.welcome_subtitle),
                    fontSize = 14.sp,
                    color = Color(0xFF94A3B8)
                )
                Spacer(modifier = Modifier.height(36.dp))
                Button(
                    onClick = { viewModel.enterApp() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = stringResource(id = R.string.btn_start), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
                Button(
                    onClick = {viewModel.setProfile()}
                ) { Text("Datos") }
                Profile(nombre = viewModel.uiState.collectAsState().value.name, matricula = viewModel.uiState.collectAsState().value.matricula)


            }
        }
    }
}

// Apartado del menu de seleccion
@Composable
fun MenuScreen(viewModel: ViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.menu_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(32.dp))

            MenuCard(
                title = stringResource(id = R.string.menu_btn_temp),
                icon = Icons.Default.Thermostat,
                accentColor = Color(0xFFFF7043)
            ) { viewModel.navigateTo(AppScreen.TEMPERATURE) }

            Spacer(modifier = Modifier.height(16.dp))

            MenuCard(
                title = stringResource(id = R.string.menu_btn_length),
                icon = Icons.Default.Straighten,
                accentColor = Color(0xFF00ACC1)
            ) { viewModel.navigateTo(AppScreen.LENGTH) }

            Spacer(modifier = Modifier.height(16.dp))

            MenuCard(
                title = stringResource(id = R.string.menu_btn_time),
                icon = Icons.Default.Schedule,
                accentColor = Color(0xFF7E57C2)
            ) { viewModel.navigateTo(AppScreen.TIME) }
        }
    }
}

@Composable
fun MenuCard(title: String, icon: ImageVector, accentColor: Color, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        modifier = Modifier.fillMaxWidth().height(72.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = accentColor.copy(alpha = 0.15f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(imageVector = icon, contentDescription = null, tint = accentColor, modifier = Modifier.size(24.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }
            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFF64748B))
        }
    }
}

// Para regresar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterScaffold(
    title: String,
    accentColor: Color,
    onBack: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        containerColor = Color(0xFF0F172A),
        topBar = {
            TopAppBar(
                title = { Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver", tint = accentColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            content = content
        )
    }
}

// Apartado de la temperatura
@Composable
fun TemperatureScreen(viewModel: ViewModel, state: AppUiState) {
    ConverterScaffold(
        title = stringResource(id = R.string.conversor_title),
        accentColor = Color(0xFFFF7043),
        onBack = { viewModel.navigateTo(AppScreen.MENU) }
    ) {
        StyledTextField(value = state.celsius, onValueChange = { viewModel.onCelsiusChanged(it) }, label = stringResource(id = R.string.label_celsius), accentColor = Color(0xFFFF7043))
        Spacer(modifier = Modifier.height(16.dp))
        StyledTextField(value = state.fahrenheit, onValueChange = { viewModel.onFahrenheitChanged(it) }, label = stringResource(id = R.string.label_fahrenheit), accentColor = Color(0xFFFF7043))
        Spacer(modifier = Modifier.height(16.dp))
        StyledTextField(value = state.kelvin, onValueChange = { viewModel.onKelvinChanged(it) }, label = stringResource(id = R.string.label_kelvin), accentColor = Color(0xFFFF7043))
    }
}

// Apartado de las longitudes
@Composable
fun LengthScreen(viewModel: ViewModel, state: AppUiState) {
    ConverterScaffold(
        title = stringResource(id = R.string.length_conversor_title),
        accentColor = Color(0xFF00ACC1),
        onBack = { viewModel.navigateTo(AppScreen.MENU) }
    ) {
        StyledTextField(value = state.meters, onValueChange = { viewModel.onMetersChanged(it) }, label = stringResource(id = R.string.label_meters), accentColor = Color(0xFF00ACC1))
        Spacer(modifier = Modifier.height(12.dp))
        StyledTextField(value = state.kilometers, onValueChange = { viewModel.onKilometersChanged(it) }, label = stringResource(id = R.string.label_kilometers), accentColor = Color(0xFF00ACC1))
        Spacer(modifier = Modifier.height(12.dp))
        StyledTextField(value = state.centimeters, onValueChange = { viewModel.onCentimetersChanged(it) }, label = stringResource(id = R.string.label_centimeters), accentColor = Color(0xFF00ACC1))
        Spacer(modifier = Modifier.height(12.dp))
        StyledTextField(value = state.feet, onValueChange = { viewModel.onFeetChanged(it) }, label = stringResource(id = R.string.label_feet), accentColor = Color(0xFF00ACC1))
        Spacer(modifier = Modifier.height(12.dp))
        StyledTextField(value = state.miles, onValueChange = { viewModel.onMilesChanged(it) }, label = stringResource(id = R.string.label_miles), accentColor = Color(0xFF00ACC1))
    }
}

// Apartado del tiempo
@Composable
fun TimeScreen(viewModel: ViewModel, state: AppUiState) {
    ConverterScaffold(
        title = stringResource(id = R.string.time_conversor_title),
        accentColor = Color(0xFF7E57C2),
        onBack = { viewModel.navigateTo(AppScreen.MENU) }
    ) {
        StyledTextField(value = state.seconds, onValueChange = { viewModel.onSecondsChanged(it) }, label = stringResource(id = R.string.label_seconds), accentColor = Color(0xFF7E57C2))
        Spacer(modifier = Modifier.height(12.dp))
        StyledTextField(value = state.minutes, onValueChange = { viewModel.onMinutesChanged(it) }, label = stringResource(id = R.string.label_minutes), accentColor = Color(0xFF7E57C2))
        Spacer(modifier = Modifier.height(12.dp))
        StyledTextField(value = state.hours, onValueChange = { viewModel.onHoursChanged(it) }, label = stringResource(id = R.string.label_hours), accentColor = Color(0xFF7E57C2))
        Spacer(modifier = Modifier.height(12.dp))
        StyledTextField(value = state.days, onValueChange = { viewModel.onDaysChanged(it) }, label = stringResource(id = R.string.label_days), accentColor = Color(0xFF7E57C2))
    }
}

@Composable
fun StyledTextField(value: String, onValueChange: (String) -> Unit, label: String, accentColor: Color) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color(0xFF94A3B8)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = accentColor,
            unfocusedBorderColor = Color(0xFF334155),
            focusedLabelColor = accentColor,
            cursorColor = accentColor,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color(0xFF1E293B),
            unfocusedContainerColor = Color(0xFF1E293B)
        )
    )
}

@Composable
fun Profile (modifier: Modifier = Modifier, nombre: String, matricula: String){
    Card (
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B).copy(alpha = 0.9f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ){
        Column(
            modifier = modifier.fillMaxWidth().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = nombre,
                fontSize = 20.sp
            )
            Text(
                text = matricula,
                fontSize = 20.sp
            )
        }
    }
}