package com.aold.advers.presentation.parts.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PickerExample() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        val values = remember {  (20..95).map { it.toString() } }
        val valuesPickerState = rememberPickerState()
        //val units = remember { listOf("°C", "℉") }
        val units = remember { listOf("°C") }
        val unitsPickerState = rememberPickerState()

        Row(modifier = Modifier
            .width(350.dp)
            .background(Color.Transparent)) {
            Picker(
                state = valuesPickerState,
                items = values,
                visibleItemsCount = 3,
                modifier = Modifier.weight(0.3f),
                textModifier = Modifier.padding(8.dp),
                textStyle = TextStyle(fontSize = 32.sp, color = Color.White)
            )
//            Picker(
//                state = unitsPickerState,
//                items = units,
//                visibleItemsCount = 3,
//                modifier = Modifier.weight(0.7f),
//                textModifier = Modifier.padding(8.dp),
//                textStyle = TextStyle(fontSize = 32.sp, color = Color.White)
//            )
        }
        Text(
            text = "Выбранная температура: "+" + ${valuesPickerState.selectedItem} ${unitsPickerState.selectedItem}" + "°C",
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}