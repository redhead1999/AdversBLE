package com.aold.advers.ble.domain.bleparsables

import com.aold.advers.ble.domain.models.UUID_DEFAULT
import com.aold.advers.ble.utils.toHex
import timber.log.Timber

object ADVERSBLEDOM : ParsableUuid("D973F2E1$UUID_DEFAULT".lowercase()) {

    const val FULL_ADVERSBLE_DOM = "D973F2E1-B19E-11E2-9E96-0800200C9A66"


    //todo команды для запуска
    const val ON = "0x7E0004F00001FF00EF"
    const val OFF = "0x7E0004000000FF00EF"
    const val COLOR = "0x7E000503xxxxxx00EF"
    const val BRIGHTNESS = "0x7E0001xx00000000EF"

    const val STORY_POWER_ON = 0
    const val STORY_START_COMMAND = 1
    const val STORY_STOP_COMMAND = 2
    const val STORY_VENTILATION_COMMAND = 3
    const val STORY_PUMP_COMMAND = 4
    const val STORY_IGNITION = 5
    const val STORY_RUNNING = 6
    const val STORY_STANDBY = 7
    const val STORY_BLOWING = 8
    const val STORY_STOP = 9
    const val STORY_VENTILATION = 10
    const val STORY_PUMP = 11
    const val STORY_CHANGE_SETUP = 12
    const val STORY_FAULT_CODE = 128

    const val MODE_WAIT = 1
    const val MODE_START = 2
    const val MODE_WORK = 3
    const val MODE_STOP = 4
    const val MODE_AIR = 5
    const val MODE_WORK_ECO = 9
    const val MODE_PUMP = 10

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
        val onOffByte = bytes[2]
        val onOffParam = bytes[5]

        Timber.d(bytes.toHex())

        return !(onOffByte.toInt() == 4 && onOffParam.toInt() == 0)
    }

    fun getLedStatus(bytes: ByteArray): String {
        val firstBytes = bytes.copyOfRange(0, 8)
        val nextBytes = bytes.copyOfRange(9, 14)
        val finalByes = bytes.copyOfRange(15, 24)

        return firstBytes.toHex()

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

