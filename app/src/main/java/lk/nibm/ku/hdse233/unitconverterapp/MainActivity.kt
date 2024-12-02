package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverter()
        }
    }
}

@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var conversionType by remember { mutableStateOf("Distance") }
    var isExpanded by remember { mutableStateOf(false) }

    fun convert() {
        val inputDouble = inputValue.toDoubleOrNull() ?: 0.0
        outputValue = when (conversionType) {
            "Distance" -> "${inputDouble * 1000} Meters" // Kilometers to Meters
            "Temperature" -> "${((inputDouble - 32) * 5 / 9).roundToInt()} °C" // Fahrenheit to Celsius
            "Weight" -> "${inputDouble / 1000} Kilograms" // Grams to Kilograms
            else -> "Invalid"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Heading
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF2C6689),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        // Subheading
        Text(
            text = "Convert between Distance, Temperature, and Weight",
            fontSize = 18.sp,
            color = Color(0xFF2C6689),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Input Field
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convert()
            },
            label = { Text("Enter value") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Conversion Type Dropdown
        Box {
            Button(onClick = { isExpanded = true }) {
                Text(text = conversionType)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Select Conversion Type")
            }
            DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(
                    text = { Text("Distance (Kilometers to Meters)") },
                    onClick = {
                        conversionType = "Distance"
                        isExpanded = false
                        convert()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Temperature (Fahrenheit to Celsius)") },
                    onClick = {
                        conversionType = "Temperature"
                        isExpanded = false
                        convert()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Weight (Grams to Kilograms)") },
                    onClick = {
                        conversionType = "Weight"
                        isExpanded = false
                        convert()
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Output Result
        Text(
            text = "Result: $outputValue",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF2C6689),
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}
