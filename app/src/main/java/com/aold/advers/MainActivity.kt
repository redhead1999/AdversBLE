package com.aold.advers

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.aold.advers.ble.handlers.BleObserver
import com.aold.advers.ble.presentation.theme.AdversBleTheme
import com.aold.advers.ble.utils.SharedPreferencesHelper
import com.aold.advers.ble.utils.windowinfo.getFoldableInfoFlow
import com.aold.advers.ble.utils.windowinfo.getWindowLayoutType
import com.aold.advers.ble.utils.windowinfo.getWindowSizeClasses
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Random

private val device_ID: IntArray = IntArray(16)
private var isSavedID = false
private var prefs: SharedPreferences? = null

private const val APP_PREFERENCES_ISID = "ISID"
private const val APP_PREFERENCES_ID = "ID"
private const val APP_PREFERENCES = "mysettings"

private var string_buffer: Array<String> = TODO()

// запуск и остановка
private const val DEVICE_ID = 5

const val TAG = "SharedPreferencesHelper"

class MainActivity : ComponentActivity() {
    lateinit var sharedPreferencesHelper  : SharedPreferencesHelper
    @SuppressLint("HardwareIds")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val deviceID = getSharedPreferences(
                "mysettings", Context.MODE_PRIVATE)
            Timber.d("Ключ устройства -" + deviceID)
        }

//        lifecycleScope.launch {
//            val prefs = getSharedPreferences(
//                APP_PREFERENCES,
//                MODE_PRIVATE
//            )
//            if (prefs.contains(APP_PREFERENCES_ISID)) {
//                isSavedID =
//                    prefs.getBoolean(APP_PREFERENCES_ISID, false)
//            }
//            if (isSavedID) {
//                loadText()
//                if (prefs.contains(APP_PREFERENCES_ID)) {
//                    for (i in string_buffer.indices) {
//                        device_ID[i] = string_buffer[i].toInt()
//                    }
//                    val key = IntArray(8)
//                    //devices.getKeyDevice(key)
//                    for (i in 0..7) {
//                        device_ID[i + 8] = key[i]
//                        Log.i(TAG, "key: " + key[i])
//                    }
//                }
//            } else {
//                generateDeviceId()
//            }
//            fun loadText() {
//                isSavedID =
//                    prefs!!.getBoolean(APP_PREFERENCES_ISID, false)
//                val savedString =
//                    prefs!!.getString(APP_PREFERENCES_ID, "")
//                string_buffer =
//                    savedString!!.split("[,]+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//            }
//            Timber.d("Ключ устройства" + device_ID)
//        }

        Timber.d("activity created...")
        Timber.d("activity created...")
        val bleObserver = BleObserver(this)
        this.lifecycle.addObserver(bleObserver)

        /*try {
            throw RuntimeException("RELEASE_TEST")
        } catch (e: Exception) {
            Timber.e(e, "test", "RELEASE")
        }*/

        //val deleteNotSeenRequest: WorkRequest = get(named("DeleteNotSeenWorker"))

        /*
                WorkManager
                    .getInstance(this)
                    .enqueue(deleteNotSeenRequest)
        */

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

    private fun generateDeviceId() {
        val random = Random()
        for (i in 0..15) {
            device_ID[i] = random.nextInt(255) + 1
        }
        saveText()
    }

    fun loadText() {
        isSavedID =
            prefs!!.getBoolean(APP_PREFERENCES_ISID, false)
        val savedString =
            prefs!!.getString(APP_PREFERENCES_ID, "")
        string_buffer =
            savedString!!.split("[,]+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }

    private fun saveText() {
        isSavedID = true
        //SharedPreferences sPref = getSharedPreferences("LOGIN_DATA", MODE_PRIVATE);
        //SharedPreferences.Editor ed = sPref.edit();
        val ed = prefs!!.edit()
        ed.putBoolean(APP_PREFERENCES_ISID, isSavedID)
        val str = StringBuilder()
        for (j in device_ID) {
            str.append(j).append(",")
        }
        ed.putString(APP_PREFERENCES_ID, str.toString())
        ed.apply()
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