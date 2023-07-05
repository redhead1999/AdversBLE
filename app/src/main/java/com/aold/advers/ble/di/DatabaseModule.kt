package com.aold.advers.ble.di

import android.app.Application
import androidx.room.Room
import com.aold.advers.ble.domain.interfaces.IBleRepository
import com.aold.advers.ble.local.BleDao
import com.aold.advers.ble.local.BleDatabase
import com.aold.advers.ble.local.BleRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDataBase(application: Application): BleDatabase {
        return Room.databaseBuilder(application, BleDatabase::class.java, "ble")
            .createFromAsset("database/ble.db")
            .build()
    }

    fun provideDao(dataBase: BleDatabase): BleDao {
        return dataBase.bleDao()
    }

    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
    single<IBleRepository> { BleRepository(get()) }

}