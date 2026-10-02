package com.cristo.tempconvert.ViewsUI

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TemperatureUiState(
    val celsius: String = "",
    val fahrenheit: String = "",
    val kelvin: String = "",
    val showWelcome: Boolean = true
)

class ViewModel : androidx.lifecycle.ViewModel() {

    private val _uiState = MutableStateFlow(TemperatureUiState())
    val uiState: StateFlow<TemperatureUiState> = _uiState.asStateFlow()

    fun enterApp() {
        _uiState.value = _uiState.value.copy(showWelcome = false)
    }

    // Cuando el usuario escribe en Celsius
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

    // Cuando el usuario escribe en Fahrenheit
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

    // Cuando el usuario escribe en Kelvin
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
}