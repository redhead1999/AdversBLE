package com.aold.advers.local.room

import androidx.room.Dao
import androidx.room.Insert
import com.aold.advers.ble.local.entities.BleCharacteristic
import com.aold.advers.ble.local.entities.Company
import com.aold.advers.ble.local.entities.Descriptor
import com.aold.advers.ble.local.entities.MicrosoftDevice
import com.aold.advers.ble.local.entities.Service

@Dao
interface TestDao {

    @Insert
    fun insertServices(services: List<Service>)

    @Insert
    fun insertCompanies(companies: List<Company>)

    @Insert
    fun insertMicrosoftDevices(devices: List<MicrosoftDevice>)

    @Insert
    fun insertCharacteristics(characteristics: List<BleCharacteristic>)

    @Insert
    fun insertDescriptors(descriptor: List<Descriptor>)

}