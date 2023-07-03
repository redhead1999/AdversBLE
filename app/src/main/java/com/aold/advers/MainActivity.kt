package com.aold.advers

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.aold.advers.presentation.navigation.Navigation
import com.aold.advers.presentation.notifications.CounterNotificationService
import com.aold.advers.ui.theme.AdversBluetoothTheme
import com.aold.advers.utils.WindowInfo
import com.aold.advers.utils.rememberWindowInfo
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Kirilin Yury on 09.06.2023.
 */
@RequiresApi(Build.VERSION_CODES.M)
@AndroidEntryPoint

class MainActivity : ComponentActivity(
) {

    private val bluetoothManager by lazy {
        applicationContext.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var darkMode by mutableStateOf(true)
        val service = CounterNotificationService(applicationContext)
        //TODO ориентация ландшафт
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        val enableBluetoothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { /* Not needed */ }

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { perms ->
            val canEnableBluetooth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                perms[Manifest.permission.BLUETOOTH_CONNECT] == true
            } else true

            if (canEnableBluetooth && !isBluetoothEnabled) {
                enableBluetoothLauncher.launch(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                )
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                )
            )
        }

        setContent {
            AdversBluetoothTheme(darkTheme = darkMode) {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current
                }
                Navigation(
                    onBluetoothStateChanged = {
                        showBluetoothDialog()
                    }
                )
                val windowInfo = rememberWindowInfo()
                if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {

                }
            }
        }
    }

    private fun promptEnableBluetooth() {
        if (!bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, )
        }
    }

    private fun startActivityForResult(enableBtIntent: Intent) {

    }

    override fun onResume() {
        super.onResume()
        if (!bluetoothAdapter.isEnabled) {
            promptEnableBluetooth()
        }
    }

   

    override fun onStart() {
        super.onStart()
        showBluetoothDialog()
    }


    private var isBluetootDialogAlreadyShown = false
    private fun showBluetoothDialog() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (!bluetoothAdapter?.isEnabled!!) {
                if (!isBluetootDialogAlreadyShown) {
                    val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startBluetoothIntentForResult.launch(enableBluetoothIntent)
                    isBluetootDialogAlreadyShown = true
                }
            }
        }
    }

    private val startBluetoothIntentForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            isBluetootDialogAlreadyShown = false
            if (result.resultCode != Activity.RESULT_OK) {
                showBluetoothDialog()
            }
        }
}