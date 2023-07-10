package com.aold.advers.sharedtest.previews

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aold.advers.ble.presentation.scan.ScannedDevice
import com.aold.advers.ble.presentation.theme.AdversBleTheme
import com.aold.advers.sharedtest.newDevice

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ScannedDevicePreview() {
    val device = newDevice
    AdversBleTheme {
        Surface() {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                ScannedDevice(device = device, onClick = {}, onForget = {},
                onFavorite = {})

            }
        }
    }
}
