package com.cristo.tempconvert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cristo.tempconvert.ui.theme.TempConvertTheme
import com.cristo.tempconvert.ViewsUi.TempCalculatorPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TempConvertTheme {
                TempCalculatorPage()
            }
        }
    }
}