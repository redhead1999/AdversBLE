package com.aold.advers.ble.handlers

class KotlinPackages internal constructor(
    var isSetter: Boolean,
    var typeParam: IntArray,
    var numParam: IntArray,
    var param: LongArray
) {
    lateinit var packet: ByteArray
    var isEmpty = false
    var isWait = false
    fun valToPacket(): Boolean {
        packet = ByteArray(20)
        packet[0] = 2
        var i = 0
        i = 0
        while (i <= 2) {
            if (isSetter) {
                packet[1 + i * 3] = 128.toByte()
            } else {
                packet[1 + i * 3] = 0.toByte()
            }
            packet[1 + i * 3] = (packet[1 + i * 3] + typeParam[i]).toByte()
            packet[2 + i * 3] = numParam[i].toByte()
            packet[3 + i * 3] = param[i].toInt().toByte()
            i++
        }

        packet[19] = 0
        return true
    }

    fun packetToVal(): Boolean {
        if (packet[0].toInt() == 0x02) {
            isSetter = packet[1].toInt() and 0x80 == 0x80
            isEmpty = packet[1].toInt() and 0x40 == 0x40
            isWait = packet[1].toInt() and 0x20 == 0x20
            typeParam = IntArray(3)
            numParam = IntArray(3)
            param = LongArray(3)
            for (i in 0..2) {
                typeParam[i] = packet[1 + i * 3].toInt() and 0x0f
                numParam[i] = packet[2 + i * 3].toInt()
                param[i] =
                    (packet[3 + i * 3] + (packet[4 + i * 3].toInt() shl 8) + (packet[5 + i * 3].toInt() shl 8) + (packet[6 + i * 3].toInt() shl 8)).toLong()
            }
        } else return false
        return true
    }
}
