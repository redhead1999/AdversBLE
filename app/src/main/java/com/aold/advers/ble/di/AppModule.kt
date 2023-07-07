package com.aold.advers.ble.di

import  android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.SharedPreferences
import com.aold.advers.ble.domain.interfaces.IAnalytics
import com.aold.advers.ble.handlers.BleGatt
import com.aold.advers.ble.handlers.BleManager
import com.aold.advers.ble.presentation.control.ControlViewModel
import com.aold.advers.ble.presentation.scan.ScanViewModel
import com.aold.advers.ble.utils.logging.Analytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.internal.common.CommonUtils.getSharedPrefs
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single { Firebase.analytics }
    single<IAnalytics> { Analytics(get()) }

    single{
        getSharedPrefs(androidApplication())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }


fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences("deviceID", android.content.Context.MODE_PRIVATE)
}


    fun provideBluetoothManager(app: Application): BluetoothManager {
        return app.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    }

    single<BluetoothAdapter> { provideBluetoothManager(androidApplication()).adapter }

    factory(named("IODispatcher")) {
        Dispatchers.IO
    }

    factory { CoroutineScope(get<CoroutineDispatcher>(named("IODispatcher")) + Job()) }

    single { BleManager(get(), get(), get()) }
    single { BleGatt(androidApplication(), get(), get(), get(), get(), get()) }

    viewModel { ScanViewModel(get(), get(), get(), get(named("IODispatcher")), get()) }
    viewModel { ControlViewModel(get(), get(), get(named("IODispatcher")), get()) }
}