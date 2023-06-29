package com.aold.advers.data.ble

import java.util.HashMap

//todo аттрибуты устройств
object SampleGattAttributes {
    var attributes: HashMap<String, String> = HashMap()
    var ADVERS_HEATER = "00002a37-0000-1000-8000-00805f9b34fb"
    var CLEINT_DEVICE = "00002902-0000-1000-8000-00805f9b34fb"

    init {
        // Sample Services.
        attributes.put("0000180d-0000-1000-8000-00805f9b34fb", "BLE-адаптер")
        attributes.put("0000180a-0000-1000-8000-00805f9b34fb", "Информация о девайсе")
        // Sample Characteristics.
        attributes.put(CLEINT_DEVICE, "Какой-то параметр")
        attributes.put("00002a29-0000-1000-8000-00805f9b34fb", "Производитель")
        // Using unknown GATT profile, must debug other end
        attributes.put("19B10000-E8F2-537E-4F6C-D104768A1214", "ioTank")
    }


    fun lookup(uuid: String, defaultName: String): String {
        val name = attributes.get(uuid)
        return name ?: defaultName
    }
}