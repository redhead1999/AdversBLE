package com.aold.advers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aold.advers.ble.handlers.BleObserver
import com.aold.advers.ble.presentation.theme.AdversBleTheme
import com.aold.advers.ble.utils.windowinfo.getFoldableInfoFlow
import com.aold.advers.ble.utils.windowinfo.getWindowLayoutType
import com.aold.advers.ble.utils.windowinfo.getWindowSizeClasses
import timber.log.Timber

/**
 * @author Kirilin Yury on 09.06.2023.
 */

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("activity created...")

        val bleObserver = BleObserver(this)
        this.lifecycle.addObserver(bleObserver)

        /*try {
            throw RuntimeException("RELEASE_TEST")
        } catch (e: Exception) {
            Timber.e(e, "test", "RELEASE")
        }*/

        //val deleteNotSeenRequest: WorkRequest = get(named("DeleteNotSeenWorker"))

        /*
                WorkManager
                    .getInstance(this)
                    .enqueue(deleteNotSeenRequest)
        */

        val devicePostureFlow = getFoldableInfoFlow(this)

        setContent {

            val windowSize = getWindowSizeClasses(this)
            val devicePosture by devicePostureFlow.collectAsStateWithLifecycle()

            val appLayoutInfo = getWindowLayoutType(
                windowInfo = windowSize,
                foldableInfo = devicePosture
            )

            AdversBleTheme {
                BleApp(appLayoutInfo)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdversBleTheme {
        TextField(value = "Test", onValueChange = {})
    }
}