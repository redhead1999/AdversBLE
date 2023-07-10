package com.aold.advers.ble.handlers

import com.aold.advers.ble.domain.interfaces.FlagsExchange

class KotlinPackages {

    //переменные в пакеты
    fun valToPacket(
        isSetter: Boolean,
        typeParam: IntArray,
        numParam: IntArray,
        param: LongArray
    ) {
        val packet = ByteArray(20)
        packet[0] = 0x01

        for (i in 0..2) {
            if (isSetter) {
                packet[1] = 0x80.toByte()
            } else {
                packet[1] = 0x00.toByte()
            }
            packet[1 + i * 3] = (packet[1 + i * 3].toInt() + typeParam[i]).toByte()

            //package2
            packet[2 + i * 3] = numParam[i].toByte()
            //package3
            packet[3 + i * 3] = param[i].toByte()
        }
        packet[19] = 0 //todo CRC
    }

    //пакет в переменные
    fun packetToVal(
        isSetter: FlagsExchange,
        typeParam: FlagsExchange,
        numParam: FlagsExchange,
        param: FlagsExchange
    ) {
        val packet = ByteArray(20)
        packet[0] = 0x02

        for (i in 0..2) {
            //todo
        }
//            if (isSetter) {
//
//            } else {
//                packet[1] = 0x00.toByte()
//            }
//            packet[1 + i * 3] = (packet[1 + i * 3].toInt() + typeParam[i]).toByte()
//
//            //package2
//            packet[2 + i * 3] = numParam[i].toByte()
//            //package3
//            packet[3 + i * 3] = param[i].toByte()
//        }
//        packet[19] = 0 //todo CRC
    }
}