package com.aold.advers.util

import android.util.Log
import com.aold.advers.local.room.TestBleDatabase
import com.aold.advers.sharedtest.characteristics
import com.aold.advers.sharedtest.companies
import com.aold.advers.sharedtest.descriptors
import com.aold.advers.sharedtest.msDevices
import com.aold.advers.sharedtest.services
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class KoinTestPerMethod(
    private val bleDatabase: TestBleDatabase
): BeforeEachCallback, AfterEachCallback {
    override fun beforeEach(context: ExtensionContext?) {
        Log.d("debugTest", "Inserting prepopulated data...")
        with(bleDatabase.testDao()) {
            insertServices(services)
            insertCompanies(companies)
            insertMicrosoftDevices(msDevices)
            insertCharacteristics(characteristics)
            insertDescriptors(descriptors)
        }
    }

    override fun afterEach(context: ExtensionContext?) {
        Log.d("debugTest", "Closing db...")
        bleDatabase.close()
    }


}