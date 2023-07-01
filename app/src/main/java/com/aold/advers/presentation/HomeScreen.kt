package com.aold.advers.presentation

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aold.advers.R
import com.aold.advers.utils.WindowInfo
import com.aold.advers.presentation.components.picker.PickerExample
import com.aold.advers.presentation.navigation.Screen
import com.aold.advers.utils.rememberWindowInfo

/**
 * @author Kirilin Yury on 09.06.2023.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StartScreen(
    navController: NavController,
) {
    val windowInfo = rememberWindowInfo()
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                 .fillMaxHeight(),
            contentAlignment = Alignment.TopCenter
        )
        {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(100.dp)
                        .height(55.dp),
                    onClick = {
                        navController.navigate(Screen.TemperatureChartScreen.route) {
                            popUpTo(Screen.TemperatureChartScreen.route) {
                                inclusive = false
                            }
                        }
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.termo),
                        contentDescription = null // decorative element
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "0°C",
                        style = androidx.compose.ui.text.TextStyle(fontSize = 10.sp)
                    )
                }
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(100.dp)
                        .height(55.dp),
                    onClick = {
                        navController.navigate(Screen.BleOperationsActivity.route) {
                            popUpTo(Screen.BleOperationsActivity.route) {
                                inclusive = true
                            }
                        }
                    }) {
                    Text(
                        text = "ПОИСК",
                        style = androidx.compose.ui.text.TextStyle(fontSize = 15.sp)
                    )
                }
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(100.dp)
                        .height(55.dp),
                    onClick = {
                        navController.navigate(Screen.VoltageChartScreen.route) {
                            popUpTo(Screen.VoltageChartScreen.route) {
                                inclusive = false
                            }
                        }
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.light),
                        contentDescription = null // decorative element
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "0.0 V",
                        style = androidx.compose.ui.text.TextStyle(fontSize = 15.sp)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
                .fillMaxHeight(),
            contentAlignment = Alignment.TopCenter
        )
        {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(100.dp)
                        .height(55.dp),
                    onClick = {
                        navController.navigate(Screen.TemperatureChartScreen.route) {
                            popUpTo(Screen.TemperatureChartScreen.route) {
                                inclusive = false
                            }
                        }
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.termo),
                        contentDescription = null // decorative element
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "0°C",
                        style = androidx.compose.ui.text.TextStyle(fontSize = 10.sp)
                    )
                }
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(100.dp)
                        .height(55.dp),
                    onClick = {
                        navController.navigate(Screen.ConnectionStartScreen.route) {
                            popUpTo(Screen.ConnectionStartScreen.route) {
                                inclusive = true
                            }
                        }
                    }) {
                    Text(
                        text = "ПОИСК",
                        style = androidx.compose.ui.text.TextStyle(fontSize = 15.sp)
                    )
                }
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(100.dp)
                        .height(55.dp),
                    onClick = {
                        navController.navigate(Screen.VoltageChartScreen.route) {
                            popUpTo(Screen.VoltageChartScreen.route) {
                                inclusive = false
                            }
                        }
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.light),
                        contentDescription = null // decorative element
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "0.0 V",
                        style = androidx.compose.ui.text.TextStyle(fontSize = 15.sp)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .width(1000.dp)
                .fillMaxHeight()
                .background(color = Color.Transparent),
            contentAlignment = Alignment.CenterEnd
        )
        {
            Row {
                PickerExample()
            }
            Row() {
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = CircleShape,
                    modifier = Modifier
                        .width(65.dp)
                        .height(40.dp),
                    onClick = {
                        navController.navigate(Screen.TestScreen.route) {
                            popUpTo(Screen.TestScreen.route) {
                                inclusive = false
                            }
                        }
                    }) {
                    Text(
                        text = "ТЕСТ",
                        style = androidx.compose.ui.text.TextStyle(fontSize = 10.sp)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Transparent),
            contentAlignment = Alignment.BottomEnd
        )
        {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {

                val isShowing = remember { mutableStateOf(false) }
                val context = LocalContext.current

                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    onClick = {
                        Toast.makeText(
                            context,
                            "Команда отправлена",
                            Toast.LENGTH_SHORT
                        ).show()
//                        lifecycle.launch {
//                            snackbarHostState.showSnackbar(
//                                message = "Подогрев запущен",
//                                actionLabel = "ОК",
//                                duration = SnackbarDuration.Short
//                            )
//                        }
                    }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.air),
                            contentDescription = null // decorative element
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = stringResource(id = R.string.heat_string),
                            style = TextStyle(fontSize = 13.sp)
                        )
                    }
                }
//
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    onClick = {
                        navController.navigate(Screen.TimerScreen.route) {
                            popUpTo(Screen.StartScreen.route) {
                                inclusive = false
                            }
                        }
                    }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.timer),
                            contentDescription = null // decorative element
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = stringResource(id = R.string.timer_string),
                            style = androidx.compose.ui.text.TextStyle(fontSize = 13.sp)
                        )
                    }
                }
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    onClick = {
                        navController.navigate(Screen.SettingsScreen.route) {
                            popUpTo(Screen.StartScreen.route) {
                                inclusive = false
                            }
                        }
                    }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.settings),
                            contentDescription = null // decorative element
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = stringResource(id = R.string.settings_string),
                            style = androidx.compose.ui.text.TextStyle(fontSize = 13.sp)
                        )
                    }
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.TopCenter
        )
        {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(200.dp)
                        .height(55.dp),
                    onClick = {
                        navController.navigate(Screen.TemperatureChartScreen.route) {
                            popUpTo(Screen.TemperatureChartScreen.route) {
                                inclusive = false
                            }
                        }
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.termo),
                        contentDescription = null // decorative element
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "0°C",
                        style = androidx.compose.ui.text.TextStyle(fontSize = 10.sp)
                    )
                }
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(200.dp)
                        .height(55.dp),
                    onClick = {
                        navController.navigate(Screen.BluetoothChatScreen.route) {
                            popUpTo(Screen.BluetoothChatScreen.route) {
                                inclusive = false
                            }
                        }
                    }) {
                    Text(
                        text = "ПОИСК",
                        style = androidx.compose.ui.text.TextStyle(fontSize = 15.sp)
                    )
                }
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(200.dp)
                        .height(55.dp),
                    onClick = {
                        navController.navigate(Screen.VoltageChartScreen.route) {
                            popUpTo(Screen.VoltageChartScreen.route) {
                                inclusive = false
                            }
                        }
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.light),
                        contentDescription = null // decorative element
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "0.0 V",
                        style = androidx.compose.ui.text.TextStyle(fontSize = 15.sp)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .width(1000.dp)
                .height(1000.dp)
                .background(color = Color.Transparent),
            contentAlignment = Alignment.CenterEnd
        )
        {
            Column() {
                Spacer(modifier = Modifier.height(15.dp))
                PickerExample(
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Transparent),
            contentAlignment = Alignment.BottomEnd
        )
        {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {

                val isShowing = remember { mutableStateOf(false) }
                val context = LocalContext.current

                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    onClick = {
                        Toast.makeText(
                            context,
                            "Команда отправлена",
                            Toast.LENGTH_SHORT
                        ).show()
//                        lifecycle.launch {
//                            snackbarHostState.showSnackbar(
//                                message = "Подогрев запущен",
//                                actionLabel = "ОК",
//                                duration = SnackbarDuration.Short
//                            )
//                        }
                    }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.air),
                            contentDescription = null // decorative element
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = stringResource(id = R.string.heat_string),
                            style = TextStyle(fontSize = 13.sp)
                        )
                    }
                }
//
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    onClick = {
                        navController.navigate(Screen.TimerScreen.route) {
                            popUpTo(Screen.StartScreen.route) {
                                inclusive = false
                            }
                        }
                    }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.timer),
                            contentDescription = null // decorative element
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = stringResource(id = R.string.timer_string),
                            style = androidx.compose.ui.text.TextStyle(fontSize = 13.sp)
                        )
                    }
                }
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    onClick = {
                        navController.navigate(Screen.SettingsScreen.route) {
                            popUpTo(Screen.StartScreen.route) {
                                inclusive = false
                            }
                        }
                    }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.settings),
                            contentDescription = null // decorative element
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = stringResource(id = R.string.settings_string),
                            style = androidx.compose.ui.text.TextStyle(fontSize = 13.sp)
                        )
                    }
                }
            }
        }
    }
}
