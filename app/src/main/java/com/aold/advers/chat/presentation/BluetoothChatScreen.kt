package com.aold.advers.chat.presentation

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aold.advers.chat.presentation.components.ChatScreen
import com.aold.advers.chat.presentation.components.DeviceScreen
import com.aold.advers.presentation.notifications.CounterNotificationService

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun BluetoothChatScreen(
    navController: NavController,
) {
    val viewModel = hiltViewModel<BluetoothViewModel>()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val service = CounterNotificationService(context)

    LaunchedEffect(key1 = state.errorMessage) {
        state.errorMessage?.let { message ->
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(key1 = state.isConnected) {
        if(state.isConnected) {
            Toast.makeText(
                context,
                "Успешно!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Surface(
    color = MaterialTheme.colors.background
    ) {
        when {
            state.isConnecting -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Text(text = "Подключение...")
                }
            }
            state.isConnected -> {
                ChatScreen(
                    state = state,
                    onDisconnect = viewModel::disconnectFromDevice,
                    onSendMessage = viewModel::sendMessage
                )
            }
            else -> {
                DeviceScreen(
                    state = state,
                    onStartScan = viewModel::startScan,
                    onStopScan = viewModel::stopScan,
                    onDeviceClick = viewModel::connectToDevice,
                    onStartServer = viewModel::waitForIncomingConnections
                )
            }
        }
    }
}