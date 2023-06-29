package com.aold.advers.presentation

import android.bluetooth.BluetoothAdapter
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.aold.advers.presentation.permissions.SystemBroadcastReceiver
import com.google.accompanist.permissions.ExperimentalPermissionsApi

/**
 * @author Kirilin Yury on 09.06.2023.
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TemperatureHumidityScreen(
    onBluetoothStateChanged:()->Unit,
    viewModel: TempHumidityViewModel = hiltViewModel()
) {

    SystemBroadcastReceiver(systemAction = BluetoothAdapter.ACTION_STATE_CHANGED) { bluetoothState ->
        val action = bluetoothState?.action ?: return@SystemBroadcastReceiver
        if (action == BluetoothAdapter.ACTION_STATE_CHANGED) {
            onBluetoothStateChanged()
        }
    }
}