package com.aold.advers.di

import com.aold.advers.ble.domain.usecases.ParseDescriptor
import com.aold.advers.ble.domain.usecases.ParseNotification
import com.aold.advers.ble.domain.usecases.ParseRead
import com.aold.advers.ble.domain.usecases.ParseScanResult
import com.aold.advers.ble.domain.usecases.ParseService
import org.koin.dsl.module

val usecasesModule = module {

    single { ParseScanResult(get()) }
    single { ParseService(get()) }
    single { ParseRead() }
    single { ParseNotification() }
    single { ParseDescriptor() }

}