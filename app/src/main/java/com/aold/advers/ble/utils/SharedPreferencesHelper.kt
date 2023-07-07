package com.aold.advers.ble.utils

import android.content.SharedPreferences
import android.util.Log
import com.aold.advers.ble.domain.models.DeviceService
import org.koin.androidx.compose.get
import timber.log.Timber
import java.util.Random

// прошивка изделия
private val device_ID: IntArray = IntArray(16)
private var isSavedID = false
private val prefs: SharedPreferences? = null

private const val APP_PREFERENCES_ISID = "ISID"
private const val APP_PREFERENCES_ID = "ID"
    private const val APP_PREFERENCES = "mysettings"

private var string_buffer: Array<String> = TODO()

const val TAG = "SharedPreferencesHelper"

class SharedPreferencesHelper(private val sharedPreferences: SharedPreferences) {
    var device_ID: String
        get() { return (sharedPreferences.getString(device_ID,"") ?: "") }
        set(value) { sharedPreferences.edit().putString(device_ID,value).commit() }

    //ГЕНЕРАЦИЯ РАНДОМНОГО КЛЮЧА
//    private fun generateDeviceId() {
//        val random = Random()
//        for (i: Int in 0..15) {
//            device_ID[i] = random.nextInt(255) + 1
//        }
//        saveData()
//        Timber.tag(TAG).d("Ключ устройства" + device_ID)
//    }
//
//    private fun getKey(devices: DeviceService) {
//        if (prefs!!.contains(APP_PREFERENCES_ISID)) {
//            isSavedID =
//                prefs!!.getBoolean(APP_PREFERENCES_ISID, false)
//        }
//        if (isSavedID) {
//            loadData()
//            if (prefs!!.contains(APP_PREFERENCES_ID)) {
//                for (i in string_buffer.indices) {
//                    device_ID[i] = string_buffer[i].toInt()
//                }
//                val key = IntArray(8)
////                devices.getKeyDevice(key)
//                for (i in 0..7) {
//                    device_ID[i + 8] = key[i]
//                    Log.i(TAG, "key: " + key[i])
//                }
//            }
//        } else {
//            generateDeviceId()
//        }
//    }

    private fun saveData() {
        isSavedID = true
        val ed: SharedPreferences.Editor? = prefs?.edit()
        ed?.putBoolean(APP_PREFERENCES_ISID, isSavedID)
        val str = StringBuilder()
        for (j in device_ID) {
            str.append(j).append(",")
        }
        ed?.putString(APP_PREFERENCES_ID, str.toString())
        ed?.apply()
    }

    private fun loadData() {
        isSavedID = prefs!!.getBoolean(APP_PREFERENCES_ISID, false)
        val savedString = prefs!!.getString(APP_PREFERENCES_ID, "")
        string_buffer =
            savedString!!.split("[,]+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }
}