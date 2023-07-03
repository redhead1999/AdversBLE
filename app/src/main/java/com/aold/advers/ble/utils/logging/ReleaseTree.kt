package com.aold.advers.ble.utils.logging

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class ReleaseTree: Timber.Tree() {

    companion object {
        const val Priority = "priority"
        const val Tag = "tag"
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, tag, message, t)

        if (priority == Log.ERROR) {
            FirebaseCrashlytics.getInstance().also {
                it.setCustomKey(Priority, priority)
                tag?.let { _ -> it.setCustomKey(Tag, tag) }
                it.log(message)
                t?.let { e -> it.recordException(e) }
            }
        }
    }
}