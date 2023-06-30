package com.aold.advers.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aold.advers.WindowInfo
import com.aold.advers.presentation.components.headings.ChartsHeaderText
import com.aold.advers.presentation.components.charts.TemperatureChart
import com.aold.advers.rememberWindowInfo

@ExperimentalMaterialApi
@Composable
fun TemperatureChartScreen(
    navController: NavController,
) {
    val data = listOf(
        Pair(6, 111.45),
        Pair(7, 111.0),
        Pair(8, 113.45),
        Pair(9, 112.25),
        Pair(10, 116.45),
        Pair(11, 113.35),
        Pair(12, 118.65),
        Pair(13, 110.15),
        Pair(14, 113.05),
        Pair(15, 114.25),
        Pair(16, 116.35),
        Pair(17, 117.45),
        Pair(18, 112.65),
        Pair(19, 115.45),
        Pair(20, 111.85)
    )

    val windowInfo = rememberWindowInfo()
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            ChartsHeaderText()
            TemperatureChart(
                data = data,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .align(CenterHorizontally)
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            ChartsHeaderText()
            Spacer(modifier = Modifier.height(50.dp))
            TemperatureChart(
                data = data,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .align(CenterHorizontally)
            )
        }
    }
}
