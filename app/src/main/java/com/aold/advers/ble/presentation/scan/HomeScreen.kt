package com.aold.advers.ble.presentation.scan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aold.advers.ble.domain.models.DeviceEvents
import com.aold.advers.ble.domain.models.ScanState
import com.aold.advers.ble.presentation.previewparams.DeviceBigParams
import com.aold.advers.ble.presentation.previewparams.DeviceLandscapeParams
import com.aold.advers.ble.presentation.previewparams.DevicePortraitParams
import com.aold.advers.ble.presentation.previewparams.FeatureParams
import com.aold.advers.ble.presentation.previewparams.LandscapeBig
import com.aold.advers.ble.presentation.previewparams.LandscapeLayouts
import com.aold.advers.ble.presentation.previewparams.PortraitLayouts
import com.aold.advers.ble.presentation.scan.device.ShowDeviceBody
import com.aold.advers.ble.presentation.scan.device.ShowDeviceDetail
import com.aold.advers.ble.presentation.theme.AdversBleTheme
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo

@Composable
fun HomeScreen(
    appLayoutInfo: AppLayoutInfo,
    scanState: ScanState,
    onControlClick: (String) -> Unit,
    deviceEvents: DeviceEvents,
    isEditing: Boolean,
    onBackClicked: () -> Unit,
    onSave: (String) -> Unit,
    onShowUserMessage: (String) -> Unit
) {

    if (appLayoutInfo.appLayoutMode.isLandscape()) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            val sidePanelModifier = Modifier.weight(2f)

            Column(
                modifier = sidePanelModifier
            ) {
                ShowDeviceDetail(
                    scanState = scanState,
                    onBackClicked = onBackClicked,
                    onControlClick = onControlClick,
                    appLayoutInfo = appLayoutInfo
                )
            }
            Column(
                modifier = Modifier.weight(3f)
            ) {
                ShowDeviceBody(
                    appLayoutInfo = appLayoutInfo,
                    scanUi = scanState.scanUI,
                    bleReadWriteCommands = scanState.bleReadWriteCommands,
                    onShowUserMessage = onShowUserMessage,
                    onEdit = deviceEvents.onIsEditing,
                    isEditing = isEditing,
                    onSave = onSave
                )
            }
        }
    } else {
        ShowDeviceDetail(
            scanState = scanState,
            onBackClicked = onBackClicked,
            onControlClick = onControlClick,
            appLayoutInfo = appLayoutInfo
        )
        Spacer(modifier = Modifier.height(24.dp))

        ShowDeviceBody(
            appLayoutInfo = appLayoutInfo,
            scanUi = scanState.scanUI,
            bleReadWriteCommands = scanState.bleReadWriteCommands,
            onShowUserMessage = onShowUserMessage,
            onEdit = deviceEvents.onIsEditing,
            isEditing = isEditing,
            onSave = onSave
        )
    }
}


@PortraitLayouts
@Composable
fun PreviewHomeScreen(
    @PreviewParameter(DevicePortraitParams::class) featureParams: FeatureParams
) {
    AdversBleTheme() {
        Column {
            HomeScreen(
                appLayoutInfo = featureParams.appLayoutInfo,
                scanState = featureParams.scanState,
                onControlClick = {},
                onShowUserMessage = {},
                onSave = {},
                isEditing = false,
                onBackClicked = {},
                deviceEvents = DeviceEvents({},{},{},{},{})
            )
        }
    }
}



@LandscapeLayouts
@Composable
fun PreviewLandscapeDeviceDetailScreen(
    @PreviewParameter(DeviceLandscapeParams::class) featureParams: FeatureParams
) {
    AdversBleTheme() {
        Column {
            HomeScreen(
                appLayoutInfo = featureParams.appLayoutInfo,
                scanState = featureParams.scanState,
                onControlClick = {},
                onShowUserMessage = {},
                onSave = {},
                isEditing = false,
                onBackClicked = {},
                deviceEvents = DeviceEvents({},{},{},{},{})
            )
        }
    }
}

@LandscapeBig
@Composable
fun PreviewLandscapeBigDeviceDetailScreen(
    @PreviewParameter(DeviceBigParams::class) featureParams: FeatureParams
) {
    AdversBleTheme() {
        Column {
            HomeScreen(
                appLayoutInfo = featureParams.appLayoutInfo,
                scanState = featureParams.scanState,
                onControlClick = {},
                onShowUserMessage = {},
                onSave = {},
                isEditing = false,
                onBackClicked = {},
                deviceEvents = DeviceEvents({},{},{},{},{})
            )
        }
    }
}
