package com.aold.advers

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.aold.advers.ble.handlers.BleObserver
import com.aold.advers.ble.utils.KEY_DEVICE
import com.aold.advers.ble.utils.windowinfo.getFoldableInfoFlow
import com.aold.advers.ble.utils.windowinfo.getWindowLayoutType
import com.aold.advers.ble.utils.windowinfo.getWindowSizeClasses
import com.aold.advers.ui.theme.AdversBleTheme
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.UUID


const val TAG = "SharedPreferencesHelper"

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("HardwareIds")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val sharedPreferences: SharedPreferences =
                getSharedPreferences(KEY_DEVICE, MODE_PRIVATE)
            val value: String? = sharedPreferences.getString("phoneKey", "")
            if (TextUtils.isEmpty(value)) {
                val phoneKey = UUID.randomUUID().toString().replace("-", "")
                val editor = sharedPreferences.edit()
                editor.putString("phoneKey", phoneKey)
                editor.commit()
                Timber.d("Ключ телефона: " + phoneKey)
            }
        }
        Timber.d("activity created...")
        val bleObserver = BleObserver(this)
        this.lifecycle.addObserver(bleObserver)

        val devicePostureFlow = getFoldableInfoFlow(this)

        setContent {

            val windowSize = getWindowSizeClasses(this)
            val devicePosture by devicePostureFlow.collectAsStateWithLifecycle()

            val appLayoutInfo = getWindowLayoutType(
                windowInfo = windowSize,
                foldableInfo = devicePosture
            )

            AdversBleTheme {
                BleApp(appLayoutInfo)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdversBleTheme {
        TextField(value = "Test", onValueChange = {})
    }
}