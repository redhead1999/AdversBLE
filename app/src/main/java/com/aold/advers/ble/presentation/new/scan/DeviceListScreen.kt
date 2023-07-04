package com.aold.advers.ble.presentation.new.scan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.aold.advers.ble.domain.models.ScanFilterOption
import com.aold.advers.ble.local.entities.ScannedDevice
import com.aold.advers.ble.presentation.new.previewparams.FeatureParams
import com.aold.advers.ble.presentation.new.previewparams.LandscapeBig
import com.aold.advers.ble.presentation.new.previewparams.LandscapeBigListParams
import com.aold.advers.ble.presentation.new.previewparams.LandscapeLayouts
import com.aold.advers.ble.presentation.new.previewparams.LandscapeListParams
import com.aold.advers.ble.presentation.new.previewparams.PortraitLayouts
import com.aold.advers.ble.presentation.new.previewparams.PortraitListParams
import com.aold.advers.ble.presentation.new.theme.SanTanScanTheme
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo
import com.aold.advers.ble.utils.windowinfo.AppLayoutMode

@Composable
fun DeviceListScreen(
    paddingValues: PaddingValues,
    devices: List<ScannedDevice>,
    onClick: (String) -> Unit,
    onFilter: (ScanFilterOption?) -> Unit,
    scanFilterOption: ScanFilterOption?,
    onFavorite: (ScannedDevice) -> Unit,
    onForget: (ScannedDevice) -> Unit,
    appLayoutInfo: AppLayoutInfo
) {
    Column(
        modifier = Modifier.padding(
            top = paddingValues.calculateTopPadding()
        )
    ) {

        if (appLayoutInfo.appLayoutMode.isLandscape()) {
            Row() {
                    ScanFilters(
                        onFilter = onFilter,
                        scanFilterOption = scanFilterOption,
                        appLayoutInfo = appLayoutInfo
                    )
                ScannedDeviceList(appLayoutInfo, devices, onClick, onFavorite, onForget)
            }
        } else {

            ScanFilters(
                onFilter = onFilter,
                scanFilterOption = scanFilterOption,
                appLayoutInfo = appLayoutInfo
            )
            ScannedDeviceList(appLayoutInfo, devices, onClick, onFavorite, onForget)
        }

    }
}

@Composable
fun ScannedDeviceList(
    appLayoutInfo: AppLayoutInfo,
    devices: List<ScannedDevice>,
    onClick: (String) -> Unit,
    onFavorite: (ScannedDevice) -> Unit,
    onForget: (ScannedDevice) -> Unit
) {

    val sidePadding = when(appLayoutInfo.appLayoutMode) {
        AppLayoutMode.PORTRAIT_NARROW -> 16.dp
        AppLayoutMode.LANDSCAPE_BIG -> 30.dp
        AppLayoutMode.LANDSCAPE_NORMAL -> 16.dp
        else -> 8.dp
    }



    LazyColumn(
        modifier = Modifier.padding(sidePadding)
    ) {
        items(devices) { device ->

            ScannedDevice(
                device = device, onClick = onClick, onFavorite = onFavorite,
                onForget = onForget
            )
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}


@PortraitLayouts
@Composable
fun PreviewDeviceListScreen(
    @PreviewParameter(PortraitListParams::class) featureParams: FeatureParams
) {
    SanTanScanTheme() {
        Column {
            DeviceListScreen(
                paddingValues = PaddingValues(),
                devices = featureParams.scannedDevice,
                onClick = {},
                onFilter = {},
                scanFilterOption = ScanFilterOption.FAVORITES,
                onFavorite = {},
                onForget = {},
                appLayoutInfo = featureParams.appLayoutInfo
            )
        }
    }
}

@LandscapeLayouts
@Composable
fun PreviewLandscapeDeviceListScreen(
    @PreviewParameter(LandscapeListParams::class) featureParams: FeatureParams
) {
    SanTanScanTheme() {
        Column {
            DeviceListScreen(
                paddingValues = PaddingValues(),
                devices = featureParams.scannedDevice,
                onClick = {},
                onFilter = {},
                scanFilterOption = ScanFilterOption.FAVORITES,
                onFavorite = {},
                onForget = {},
                appLayoutInfo = featureParams.appLayoutInfo
            )
        }
    }
}

@LandscapeBig
@Composable
fun PreviewLandscapeBigListScreen(
    @PreviewParameter(LandscapeBigListParams::class) featureParams: FeatureParams
) {
    SanTanScanTheme() {
        Column {
            DeviceListScreen(
                paddingValues = PaddingValues(),
                devices = featureParams.scannedDevice,
                onClick = {},
                onFilter = {},
                scanFilterOption = ScanFilterOption.FAVORITES,
                onFavorite = {},
                onForget = {},
                appLayoutInfo = featureParams.appLayoutInfo
            )
        }
    }
}