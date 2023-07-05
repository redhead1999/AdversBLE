package com.aold.advers.di

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.BluetoothLeScanner
import androidx.test.core.app.ApplicationProvider
import com.aold.advers.ble.handlers.BleGatt
import com.aold.advers.ble.handlers.BleManager
import com.aold.advers.local.room.TestBleDatabase
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.StandardTestDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

@OptIn(ExperimentalCoroutinesApi::class)
val testAppModule = module {

    single<Application> { ApplicationProvider.getApplicationContext() }

/*
    fun provideBluetoothManager(app: Application): BluetoothManager {
        return app.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    }
*/

    single {
        mockk<BluetoothAdapter>(relaxed = true)
    }

    single {
        mockk<BluetoothLeScanner>(relaxed = true)
    }

    factory(named("IODispatcher")) {
        StandardTestDispatcher() + Job()
    }

    factory { CoroutineScope(get(named("IODispatcher"))) }

    scope<TestBleDatabase> {
        scoped { BleManager(get(), get(), get()) }
        scoped { BleGatt(get(), get(), get(), get(), get(), get()) }
    }

    //single { BleGatt(androidApplication(), get(), get()) }

}