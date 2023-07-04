package com.aold.advers

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.aold.advers.ble.utils.logging.ReleaseTree
import com.aold.advers.presentation.notifications.CounterNotificationService
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

/**
 * @author Kirilin Yury on 09.06.2023.
 */
@HiltAndroidApp
class AdversApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree())
//        } else
//            Timber.plant(ReleaseTree())
        createNotificationChannel()

//        startKoin {
//            // Log Koin into Android logger
//            androidLogger()
//            // Reference Android context
//            androidContext(this@AdversApplication)
//            workManagerFactory()
//            // Load modules
//            //modules(appModule, databaseModule, usecasesModule)
//        }
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CounterNotificationService.COUNTER_CHANNEL_ID,
                "Counter",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Used for the increment counter notifications"

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


}