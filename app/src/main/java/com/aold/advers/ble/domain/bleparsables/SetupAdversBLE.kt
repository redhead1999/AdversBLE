package com.aold.advers.ble.domain.bleparsables

import com.aold.advers.ble.domain.models.UUID_DEFAULT
import com.aold.advers.ble.utils.toHex
import timber.log.Timber
import java.util.UUID


object ADVERSBLEDOM : ParsableUuid("D973F2E1$UUID_DEFAULT".lowercase()) {

    //UUID ДЛЯ СОЕДИНЕНИЯ
    // 0000FFF3-0000-1000-8000-00805F9B34FB
    //default - 00001101-0000-1000-8000-00805F9B34FB

    val SERIAL_SERVICE_UUID = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb")
    val TX_CHAR_UUID = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb")
    val RX_CHAR_UUID = UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb")

    // UUID for the ble serial port client characteristic which is necessary for notifications.
    val CLIENT_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")

    const val AUTOTERM_ADVERS_BLE = "D973F2E1-B19E-11E2-9E96-0800200C9A66"

    const val PERIPHERAL_PREF_CONNECTION = "0xFFFFFFFF0000FFFF"
    const val APPEARANCE = "0x0000"
    const val DEVICE_NAME = "0x50552D3238000000"

    //todo команды для запуска
    const val ON = "0x424C452070756C74"
    const val OFF = "0x7E0004000000FF00EF"
    const val COLOR = "0x7E000503xxxxxx00EF"
    const val BRIGHTNESS = "0x7E0001xx00000000EF"

    const val idDevice =""

    /*
             0x7E 00 04 00 00 00 FF 00 EF
        off: 0x7E 04 04 00 00 00 FF 00 EF
        0x7E 05 03 80 03FFFF 00EF
        0x7E 00 05 03 FF0000 00EF
        0x7E 07 05 03 00FF00 10EF
    */

    val white = "FFFFFF"
    val yellow = "FFFF00"
    val cyan = "00FFFF"
    val green = "00FF00"
    val magenta = "FF00FF"
    val blue = "0000FF"
    val orange = "FFA500"
    val red = "FF0000"

    val colorsHex = arrayOf(white, yellow, cyan, green, magenta, blue, orange, red)

    fun onOffState(bytes: ByteArray): Boolean {
        val onOffByte = bytes[6]
        val onOffParam = bytes[2]

        Timber.d(bytes.toHex())

        return !(onOffByte.toInt() == 4 && onOffParam.toInt() == 0)
    }


    fun getLedStatus(bytes: ByteArray): String {
        val firstBytes = bytes.copyOfRange(0, 8)
        val nextBytes = bytes.copyOfRange(9, 14)
        val finalByes = bytes.copyOfRange(15, 24)

        return firstBytes.toHex()
    }

    fun setSetup(bytes: ByteArray): Boolean {
        //val sPref: SharedPreferences = getSharedPreferences(idDevice, Context.MODE_PRIVATE)
        // val ed = sPref.edit()
        val setupWorkTimeDays = bytes[3]
        val setupWorkTimeHours = bytes[4]

        val onConnectionSetup = bytes[5]

        val setupWorkUnlimited = bytes[6]
        val setupWarmUpAuto = bytes[7]
        val setupWarmUpManual = bytes[8]
        val setupWarmUpTempSetpoint = bytes[9]
        val setupPreheaterTempSetpoint = bytes[10]
        val setupPumpInStandby = bytes[11]
        val setupPumpOnEngine = bytes[12]
        val setupTurnOnFurnace = bytes[13]
        val setupFurnaceTempSetpoint = bytes[14]
        val setupExternalOperating = bytes[15]
        val setupSystemCelsius = bytes[16]
        val setupSystem12Hour = bytes[17]

        Timber.d(bytes.toHex())

        return !(onConnectionSetup.toInt() == 1 && setupWarmUpAuto.toInt() == 0)

    }

    override fun commands(param: Any?): Array<String> {
        return arrayOf(
            "On: 7e0004f00001ff00ef",
            "Off: 7e0004000000ff00ef",
            "Color: 7e000503xxxxxx00ef"
        )
    }
    override fun getReadStringFromBytes(byteArray: ByteArray): String {
        TODO("Not yet implemented")
    }
}

