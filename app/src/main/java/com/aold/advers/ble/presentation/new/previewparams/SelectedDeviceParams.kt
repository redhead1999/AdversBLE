package com.aold.advers.ble.presentation.new.previewparams

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.aold.advers.ble.domain.models.BleConnectEvents
import com.aold.advers.ble.domain.models.BleReadWriteCommands
import com.aold.advers.ble.domain.models.ConnectionState
import com.aold.advers.ble.domain.models.DeviceEvents
import com.aold.advers.ble.domain.models.ScanState
import com.aold.advers.ble.domain.models.ScanUI
class DevicePortraitParams : PreviewParameterProvider<FeatureParams> {

    override val values = sequenceOf(
        FeatureParams(
            ScanState(
                ScanUI(
                    emptyList(),
                    deviceDetail,
                    ConnectionState.CONNECTING,
                    null,
                    null),
                BleConnectEvents({}, {}),
                BleReadWriteCommands(
                    {},
                    { _: String, _: String -> },
                    { _: String, _: String -> },
                    { _: String, _: String, _: String -> },
                ),
                DeviceEvents({}, {}, {},{},{})
            ),
            devices,
            deviceDetail,
            portrait
        )
    )
}
class DeviceLandscapeParams : PreviewParameterProvider<FeatureParams> {

    override val values = sequenceOf(
        FeatureParams(
            ScanState(
                ScanUI(
                    emptyList(),
                    deviceDetail,
                    ConnectionState.CONNECTING,
                    null,
                    null),
                BleConnectEvents({}, {}),
                BleReadWriteCommands(
                    {},
                    { _: String, _: String -> },
                    { _: String, _: String -> },
                    { _: String, _: String, _: String -> },
                ),
                DeviceEvents({}, {}, {},{},{})
            ),
            devices,
            deviceDetail,
            landscapeNormal
        )
    )
}
class DeviceBigParams : PreviewParameterProvider<FeatureParams> {

    override val values = sequenceOf(
        FeatureParams(
            ScanState(
                ScanUI(
                    emptyList(),
                    deviceDetail,
                    ConnectionState.CONNECTING,
                    null,
                    null),
                BleConnectEvents({}, {}),
                BleReadWriteCommands(
                    {},
                    { _: String, _: String -> },
                    { _: String, _: String -> },
                    { _: String, _: String, _: String -> },
                ),
                DeviceEvents({}, {}, {},{},{})
            ),
            devices,
            deviceDetail,
            landscapeBig
        )
    )
}
