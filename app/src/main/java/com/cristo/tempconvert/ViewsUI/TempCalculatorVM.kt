package com.cristo.tempconvert.ViewsUI

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class AppScreen {
    WELCOME,
    MENU,
    TEMPERATURE,
    LENGTH
}

data class AppUiState(
    val currentScreen: AppScreen = AppScreen.WELCOME,
    // Estados de Temperatura
    val celsius: String = "",
    val fahrenheit: String = "",
    val kelvin: String = "",
    // Estados de Longitud (Metros, Kilómetros, Centímetros, Pies, Millas)
    val meters: String = "",
    val kilometers: String = "",
    val centimeters: String = "",
    val feet: String = "",
    val miles: String = ""
)

class ViewModel : androidx.lifecycle.ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun enterApp() {
        _uiState.value = _uiState.value.copy(currentScreen = AppScreen.MENU)
    }

    fun navigateTo(screen: AppScreen) {
        _uiState.value = _uiState.value.copy(currentScreen = screen)
    }


    fun onCelsiusChanged(input: String) {
        val c = input.toDoubleOrNull()
        if (c == null) {
            _uiState.value = _uiState.value.copy(celsius = input, fahrenheit = "", kelvin = "")
            return
        }
        val f = c * 9 / 5 + 32
        val k = c + 273.15
        _uiState.value = _uiState.value.copy(
            celsius = input,
            fahrenheit = String.format("%.2f", f),
            kelvin = String.format("%.2f", k)
        )
    }

    fun onFahrenheitChanged(input: String) {
        val f = input.toDoubleOrNull()
        if (f == null) {
            _uiState.value = _uiState.value.copy(celsius = "", fahrenheit = input, kelvin = "")
            return
        }
        val c = (f - 32) * 5 / 9
        val k = c + 273.15
        _uiState.value = _uiState.value.copy(
            celsius = String.format("%.2f", c),
            fahrenheit = input,
            kelvin = String.format("%.2f", k)
        )
    }

    fun onKelvinChanged(input: String) {
        val k = input.toDoubleOrNull()
        if (k == null) {
            _uiState.value = _uiState.value.copy(celsius = "", fahrenheit = "", kelvin = input)
            return
        }
        val c = k - 273.15
        val f = c * 9 / 5 + 32
        _uiState.value = _uiState.value.copy(
            celsius = String.format("%.2f", c),
            fahrenheit = String.format("%.2f", f),
            kelvin = input
        )
    }
    

    fun onMetersChanged(input: String) {
        val m = input.toDoubleOrNull()
        if (m == null) {
            clearLengthFields()
            return
        }
        updateLengthValues(m)
    }

    fun onKilometersChanged(input: String) {
        val km = input.toDoubleOrNull()
        if (km == null) {
            clearLengthFields()
            return
        }
        updateLengthValues(km * 1000.0)
    }

    fun onCentimetersChanged(input: String) {
        val cm = input.toDoubleOrNull()
        if (cm == null) {
            clearLengthFields()
            return
        }
        updateLengthValues(cm / 100.0)
    }

    fun onFeetChanged(input: String) {
        val ft = input.toDoubleOrNull()
        if (ft == null) {
            clearLengthFields()
            return
        }
        updateLengthValues(ft / 3.28084)
    }

    fun onMilesChanged(input: String) {
        val mi = input.toDoubleOrNull()
        if (mi == null) {
            clearLengthFields()
            return
        }
        updateLengthValues(mi * 1609.34)
    }

    private fun updateLengthValues(meters: Double) {
        _uiState.value = _uiState.value.copy(
            meters = String.format("%.2f", meters),
            kilometers = String.format("%.4f", meters / 1000.0),
            centimeters = String.format("%.2f", meters * 100.0),
            feet = String.format("%.2f", meters * 3.28084),
            miles = String.format("%.4f", meters / 1609.34)
        )
    }

    private fun clearLengthFields() {
        _uiState.value = _uiState.value.copy(
            meters = "", kilometers = "", centimeters = "", feet = "", miles = ""
        )
    }
}