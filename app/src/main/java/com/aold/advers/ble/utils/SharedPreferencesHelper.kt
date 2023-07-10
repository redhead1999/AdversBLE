package com.aold.advers.ble.utils

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import androidx.activity.ComponentActivity
import com.aold.advers.ble.domain.models.DeviceService
import org.koin.androidx.compose.get
import timber.log.Timber
import java.util.Random
import java.util.UUID

// прошивка изделия
private val device_ID: IntArray = IntArray(16)
private var isSavedID = false
private val prefs: SharedPreferences? = null

private const val APP_PREFERENCES_ISID = "ISID"
private const val APP_PREFERENCES_ID = "ID"
    private const val APP_PREFERENCES = "mysettings"

private var string_buffer: Array<String> = TODO()

const val TAG = "SharedPreferencesHelper"
const val KEY_DEVICE = "phoneKey"

class SharedPreferencesHelper(private val sharedPreferences: SharedPreferences) {
    var phoneKey: String
        get() {
            return (sharedPreferences.getString(KEY_DEVICE, MODE_PRIVATE.toString())) ?: ""
        }
        set(value) {
            if (TextUtils.isEmpty(phoneKey)) {
                val phoneKey = UUID.randomUUID().toString().replace("-", "")
                val editor = sharedPreferences.edit()
                editor.putString("phoneKey", phoneKey)
                editor.apply()
                sharedPreferences.edit().putString("phoneKey", phoneKey)
                editor.apply()
            }
        }
}