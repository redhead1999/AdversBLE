package com.aold.advers.ble.presentation.scan

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aold.advers.ble.domain.models.ScanFilterOption
import com.aold.advers.ble.local.entities.ScannedDevice
import com.aold.advers.ble.presentation.previewparams.FeatureParams
import com.aold.advers.ble.presentation.previewparams.LandscapeBig
import com.aold.advers.ble.presentation.previewparams.LandscapeBigListParams
import com.aold.advers.ble.presentation.previewparams.LandscapeLayouts
import com.aold.advers.ble.presentation.previewparams.LandscapeListParams
import com.aold.advers.ble.presentation.previewparams.PortraitLayouts
import com.aold.advers.ble.presentation.previewparams.PortraitListParams
import com.aold.advers.ble.presentation.test.TestScreen
import com.aold.advers.ble.presentation.theme.AdversBleTheme
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo
import com.aold.advers.ble.utils.windowinfo.AppLayoutMode

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DeviceListScreen(
    paddingValues: PaddingValues,
    devices: List<ScannedDevice>,
    onClick: (String) -> Unit,
    onFilter: (ScanFilterOption?) -> Unit,
    scanFilterOption: ScanFilterOption?,
    onFavorite: (ScannedDevice) -> Unit,
    onForget: (ScannedDevice) -> Unit,
    navController: NavController,
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

        TestScreen(
            navController = navController,
            appLayoutInfo = appLayoutInfo,
            onBackClicked = {})
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



    LazyRow(
        modifier = Modifier.padding(sidePadding)

    ) {
        items(devices) { device ->

            ScannedDevice(
                device = device, onClick = onClick, onFavorite = onFavorite,
                onForget = onForget
            )
            Spacer(modifier = Modifier.width(30.dp))

        }
    }
}


//@RequiresApi(Build.VERSION_CODES.O)
//@PortraitLayouts
//@Composable
//fun PreviewDeviceListScreen(
//    @PreviewParameter(PortraitListParams::class) featureParams: FeatureParams
//) {
//    AdversBleTheme() {
//        Column {
//            DeviceListScreen(
//                paddingValues = PaddingValues(),
//                devices = featureParams.scannedDevice,
//                onClick = {},
//                onFilter = {},
//                scanFilterOption = ScanFilterOption.FAVORITES,
//                onFavorite = {},
//                onForget = {},
//                appLayoutInfo = featureParams.appLayoutInfo,
//                navController = navController,
//            )
//        }
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@LandscapeLayouts
//@Composable
//fun PreviewLandscapeDeviceListScreen(
//    @PreviewParameter(LandscapeListParams::class) featureParams: FeatureParams
//) {
//    AdversBleTheme() {
//        Column {
//            DeviceListScreen(
//                paddingValues = PaddingValues(),
//                devices = featureParams.scannedDevice,
//                onClick = {},
//                onFilter = {},
//                scanFilterOption = ScanFilterOption.FAVORITES,
//                onFavorite = {},
//                onForget = {},
//                appLayoutInfo = featureParams.appLayoutInfo
//            )
//        }
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@LandscapeBig
//@Composable
//fun PreviewLandscapeBigListScreen(
//    @PreviewParameter(LandscapeBigListParams::class) featureParams: FeatureParams
//) {
//    AdversBleTheme() {
//        Column {
//            DeviceListScreen(
//                paddingValues = PaddingValues(),
//                devices = featureParams.scannedDevice,
//                onClick = {},
//                onFilter = {},
//                scanFilterOption = ScanFilterOption.FAVORITES,
//                onFavorite = {},
//                onForget = {},
//                appLayoutInfo = featureParams.appLayoutInfo
//            )
//        }
//    }
//}