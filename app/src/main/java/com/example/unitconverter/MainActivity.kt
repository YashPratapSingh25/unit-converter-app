package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputValue by remember{ mutableStateOf(value = "") }
    var outputValue by remember{ mutableStateOf(value = "") }
    var iExpanded by remember { mutableStateOf(value = false) }
    var oExpanded by remember { mutableStateOf(value = false) }
    var inputUnit by remember { mutableStateOf(value = "Metre") }
    var outputUnit by remember { mutableStateOf(value = "Centimetre") }
    var iConversionFactor by remember { mutableDoubleStateOf(value = 1.0) }
    var oConversionFactor by remember { mutableDoubleStateOf(value = 100.0) }

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull()
        if(inputValueDouble == null){
            outputValue = ""
            return
        }
        val inputValueBigDecimal = BigDecimal(inputValueDouble)
        val iConversionFactorBigDecimal = BigDecimal(iConversionFactor)
        val oConversionFactorBigDecimal = BigDecimal(oConversionFactor)

        val outputValueBigDecimal = inputValueBigDecimal
            .multiply(iConversionFactorBigDecimal)
            .multiply(oConversionFactorBigDecimal)
            .setScale(6, RoundingMode.HALF_UP)
            .stripTrailingZeros()

        outputValue = outputValueBigDecimal.toPlainString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", modifier = Modifier.padding(8.dp), fontSize = 24.sp)
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            label = { Text(text = "Enter Value") }
        )

        Row (
            modifier = Modifier
                .padding(0.dp, 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box {
                Button(onClick = { iExpanded = true }, modifier = Modifier.padding(4.dp, 0.dp)) {
                    Text(text = inputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Kilometre") }, onClick = {
                        iExpanded = false
                        inputUnit = "Kilometre"
                        iConversionFactor = 1000.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Metre") }, onClick = {
                        iExpanded = false
                        inputUnit = "Metre"
                        iConversionFactor = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Centimetre") }, onClick = {
                        iExpanded = false
                        inputUnit = "Centimetre"
                        iConversionFactor = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimetre") }, onClick = {
                        iExpanded = false
                        inputUnit = "Millimetre"
                        iConversionFactor = 0.001
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Mile") }, onClick = {
                        iExpanded = false
                        inputUnit = "Mile"
                        iConversionFactor = 1609.34
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Yard") }, onClick = {
                        iExpanded = false
                        inputUnit = "Yard"
                        iConversionFactor = 0.9144
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Foot") }, onClick = {
                        iExpanded = false
                        inputUnit = "Foot"
                        iConversionFactor = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Inch") }, onClick = {
                        iExpanded = false
                        inputUnit = "Inch"
                        iConversionFactor = 0.0254
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Nautical Mile") }, onClick = {
                        iExpanded = false
                        inputUnit = "Nautical Mile"
                        iConversionFactor = 1852.0
                        convertUnits()
                    })
                }
            }
            Box {
                Button(onClick = { oExpanded = true }, modifier = Modifier.padding(4.dp, 0.dp)) {
                    Text(text = outputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Kilometre") }, onClick = {
                        oExpanded = false
                        outputUnit = "Kilometre"
                        oConversionFactor = 0.001
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Metre") }, onClick = {
                        oExpanded = false
                        outputUnit = "Metre"
                        oConversionFactor = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Centimetre") }, onClick = {
                        oExpanded = false
                        outputUnit = "Centimetre"
                        oConversionFactor = 100.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimetre") }, onClick = {
                        oExpanded = false
                        outputUnit = "Millimetre"
                        oConversionFactor = 1000.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Mile") }, onClick = {
                        oExpanded = false
                        outputUnit = "Mile"
                        oConversionFactor = 0.000621371
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Yard") }, onClick = {
                        oExpanded = false
                        outputUnit = "Yard"
                        oConversionFactor = 1.09361
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Foot") }, onClick = {
                        oExpanded = false
                        outputUnit = "Foot"
                        oConversionFactor = 3.28084
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Inch") }, onClick = {
                        oExpanded = false
                        outputUnit = "Inch"
                        oConversionFactor = 39.37
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Nautical Mile") }, onClick = {
                        oExpanded = false
                        outputUnit = "Nautical Mile"
                        oConversionFactor = 0.000539957
                        convertUnits()
                    })
                }
            }
        }
        Text(text = "Result: $outputValue $outputUnit", modifier = Modifier.padding(8.dp), fontSize = 20.sp)
    }

}