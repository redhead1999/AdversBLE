package com.aold.advers.chat.domain.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.aold.advers.chat.domain.chat.BluetoothController
import com.aold.advers.chat.domain.data.AndroidBluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @RequiresApi(Build.VERSION_CODES.M)
    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context: Context): BluetoothController {
        return AndroidBluetoothController(context)
    }
}