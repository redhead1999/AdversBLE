package com.aold.advers

import android.app.Application
import com.aold.advers.ble.di.appModule
import com.aold.advers.ble.di.databaseModule
import com.aold.advers.ble.di.usecasesModule
import com.aold.advers.ble.utils.logging.DebugTree
import com.aold.advers.ble.utils.logging.ReleaseTree
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import timber.log.Timber

/**
 * @author Kirilin Yury on 09.06.2023.
 */
class AdversApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else
            Timber.plant(ReleaseTree())

        org.koin.core.context.startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@AdversApplication)
            workManagerFactory()
            // Load modules
            modules(appModule, databaseModule, usecasesModule)
        }

    }
}


//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                CounterNotificationService.COUNTER_CHANNEL_ID,
//                "Counter",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            channel.description = "Used for the increment counter notifications"
//
//            val notificationManager =
//                ContextCompat.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }