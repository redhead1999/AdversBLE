package com.aold.advers.di.endtoend

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.BluetoothLeScanner
import androidx.test.core.app.ApplicationProvider
import com.aold.advers.ble.domain.interfaces.IAnalytics
import com.aold.advers.ble.domain.interfaces.IBleRepository
import com.aold.advers.ble.domain.usecases.ParseDescriptor
import com.aold.advers.ble.domain.usecases.ParseNotification
import com.aold.advers.ble.domain.usecases.ParseRead
import com.aold.advers.ble.domain.usecases.ParseScanResult
import com.aold.advers.ble.domain.usecases.ParseService
import com.aold.advers.ble.handlers.BleGatt
import com.aold.advers.ble.handlers.BleManager
import com.aold.advers.ble.presentation.scan.ScanViewModel
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

@OptIn(ExperimentalCoroutinesApi::class)
val endToEndModule = module {

    single<Application> { ApplicationProvider.getApplicationContext() }

    single<IAnalytics> { mockk(relaxed = true) }

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

    single<IBleRepository> { mockk(relaxed = true) }
    single { mockk<BleManager>(relaxed = true) }
    single { mockk<BleGatt>(relaxed = true) }
    //single { mockk<BleDatabase>(relaxed = true) }

    viewModel { ScanViewModel(get(), get(), get(), UnconfinedTestDispatcher(), get()) }

    single { ParseScanResult(get()) }
    single { ParseService(get()) }
    single { ParseRead() }
    single { ParseNotification() }
    single { ParseDescriptor() }

}