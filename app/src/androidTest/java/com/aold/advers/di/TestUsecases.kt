package com.aold.advers.di

import com.aold.advers.ble.domain.usecases.ParseDescriptor
import com.aold.advers.ble.domain.usecases.ParseNotification
import com.aold.advers.ble.domain.usecases.ParseRead
import com.aold.advers.ble.domain.usecases.ParseScanResult
import com.aold.advers.ble.domain.usecases.ParseService
import com.aold.advers.local.room.TestBleDatabase
import org.koin.dsl.module

val testUsecasesModule = module {

    scope<TestBleDatabase> {
        scoped { ParseScanResult(get()) }
        scoped { ParseService(get()) }
    }

    single { ParseRead() }
    single { ParseNotification() }
    single { ParseDescriptor() }
}

