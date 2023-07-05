//package com.aold.advers.presentation.components
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.aold.advers.presentation.notifications.Counter
//import com.aold.advers.presentation.notifications.CounterNotificationService
//import com.aold.advers.ui.theme.AdversBluetoothTheme
//
//@RequiresApi(Build.VERSION_CODES.M)
//@Composable
//fun TestScreen(
//    navController: NavController,
//) {
//    val context = LocalContext.current
//    val service = CounterNotificationService(context)
//    var darkMode by remember { mutableStateOf(true) }
//    AdversBluetoothTheme(
//        darkTheme = darkMode,
//    ){
//    Row() {
//        Box(
//            modifier = Modifier
//                .background(Color.Transparent)
//                .fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            Column(
//                modifier = Modifier,
//                verticalArrangement = Arrangement.spacedBy(20.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Box(modifier = Modifier.fillMaxSize()) {
//                    Button(onClick = {
//                       service.showNotification(Counter.value)
//                    }) {
//                        Text(text = "Уведомление о запуске")
//                    }
//                }
//            }
//        }
//    }
//} }