package com.aold.advers.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aold.advers.ble.local.BleDao
import com.aold.advers.ble.local.Converters
import com.aold.advers.ble.local.entities.BleCharacteristic
import com.aold.advers.ble.local.entities.Company
import com.aold.advers.ble.local.entities.Descriptor
import com.aold.advers.ble.local.entities.MicrosoftDevice
import com.aold.advers.ble.local.entities.ScannedDevice
import com.aold.advers.ble.local.entities.Service
import com.aold.advers.local.room.TestDao
@Database(
    entities = [Company::class, Service::class, BleCharacteristic::class, MicrosoftDevice::class,
        ScannedDevice::class, Descriptor::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TestBleDatabase : RoomDatabase() {
    abstract fun bleDao(): BleDao
    abstract fun testDao(): TestDao
}
