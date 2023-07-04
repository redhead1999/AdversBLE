package com.aold.advers.ble.presentation.new.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.aold.advers.R
import com.aold.advers.ble.domain.models.BleConnectEvents
import com.aold.advers.ble.domain.models.DeviceDetail
import com.aold.advers.ble.domain.models.DeviceEvents
import com.aold.advers.ble.domain.models.ScanUI
import com.aold.advers.ble.local.entities.ScannedDevice
import com.aold.advers.ble.local.entities.displayName
import com.aold.advers.ble.presentation.new.previewparams.FeatureParams
import com.aold.advers.ble.presentation.new.previewparams.LandscapeLayoutParams
import com.aold.advers.ble.presentation.new.previewparams.PortraitLayoutParams
import com.aold.advers.ble.presentation.new.scan.device.DeviceButtons
import com.aold.advers.ble.presentation.new.scan.device.DeviceMenu
import com.aold.advers.ble.presentation.new.theme.SanTanScanTheme
import com.aold.advers.ble.presentation.new.theme.appBarTitle
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo
import com.aold.advers.ble.presentation.new.previewparams.LandscapeLayouts
import com.aold.advers.ble.presentation.new.previewparams.PortraitLayouts


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBarWithBackButton(
    appLayoutInfo: AppLayoutInfo,
    onBackClicked: () -> Unit,
    scanUi: ScanUI,
    deviceDetail: DeviceDetail,
    deviceEvents: DeviceEvents,
    bleConnectEvents: BleConnectEvents,
    onControlClick: (String) -> Unit
) {

    var deviceMenuExpanded by rememberSaveable { mutableStateOf(false) }
    val connectEnabled = !scanUi.bleMessage.isActive()
    val disconnectEnabled = scanUi.bleMessage.isActive()

    CenterAlignedTopAppBar(
        //modifier = Modifier.border(2.dp, Color.Blue),
        windowInsets = WindowInsets(
            top = 0.dp,
            bottom = 0.dp
        ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF00005d),
            titleContentColor = Color(0xFFcaccd9),
            navigationIconContentColor = androidx.compose.material3.MaterialTheme.colorScheme
                .onPrimary.copy(.7f)
        ),
        title = {
            if (appLayoutInfo.appLayoutMode.isLandscape()) {
                DeviceButtons(
                    connectEnabled = connectEnabled,
                    onConnect = bleConnectEvents.onConnect,
                    device = deviceDetail.scannedDevice,
                    disconnectEnabled = disconnectEnabled,
                    onDisconnect = bleConnectEvents.onDisconnect,
                    services = deviceDetail.services,
                    onControlClick = onControlClick
                )
            } else {
                Text(
                    text = deviceDetail.scannedDevice.displayName(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = appBarTitle
                )
            }
        },
        navigationIcon = {
            androidx.compose.material3.IconButton(onClick = onBackClicked) {
                BackIcon(contentDesc = "Go Back")
            }
        },
        actions = {
            DeviceMenu(
                device = deviceDetail.scannedDevice,
                expanded = deviceMenuExpanded,
                onExpanded = { deviceMenuExpanded = it },
                onEdit = deviceEvents.onIsEditing,
                onFavorite = deviceEvents.onFavorite,
                onForget = deviceEvents.onForget
            )
        }
    )
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeAppBar(
    scanning: Boolean,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onHelp: () -> Unit,
    permissionsGranted: Boolean
) {

    var homeMenuExpanded by rememberSaveable { mutableStateOf(false) }

    androidx.compose.material3.TopAppBar(
        //modifier = Modifier.border(2.dp, Color.Blue),
        windowInsets = WindowInsets(
            top = 0.dp,
            bottom = 0.dp
        ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF00005d),
            titleContentColor = Color(0xFFcaccd9),
            navigationIconContentColor = androidx.compose.material3.MaterialTheme.colorScheme
                .onPrimary.copy(.7f)
        ),
        navigationIcon = {
            MainMenu(
                expanded = homeMenuExpanded,
                onExpanded = { homeMenuExpanded = it },
                onHelp = onHelp
            )
        },
        title = {
            androidx.compose.material3.Text(
                text = if (scanning) "Your Devices" else "Scan Stopped",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = appBarTitle
            )
        },
        actions = {
            FilledIconButton(
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                    disabledContentColor = androidx.compose.material3.MaterialTheme.colorScheme.outline
                ),
                enabled = (permissionsGranted && !scanning),
                onClick = { onStartScan() },
                content = {
                    androidx.compose.material3.Icon(
                        painter = painterResource(id = R.drawable.connect),
                        contentDescription = "Connect"
                    )
                })
            FilledIconButton(
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                    disabledContentColor = androidx.compose.material3.MaterialTheme.colorScheme.outline
                ),
                enabled = scanning,
                onClick = { onStopScan() },
                content = {
                    androidx.compose.material3.Icon(
                        painter = painterResource(id = R.drawable.disconnect),
                        contentDescription = "Disconnect"
                    )
                })
        }
    )
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BasicBackTopAppBar(
    appLayoutInfo: AppLayoutInfo,
    onBackClicked: () -> Unit,
    titleContent: @Composable () -> Unit
) {

    CenterAlignedTopAppBar(
        //modifier = Modifier.border(2.dp, Color.Blue),
        windowInsets = WindowInsets(
            top = 0.dp,
            bottom = 0.dp
        ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF00005d),
            titleContentColor = Color(0xFFcaccd9),
            navigationIconContentColor = MaterialTheme.colors.primary
        ),
        title = { titleContent() },
        navigationIcon = {
            androidx.compose.material3.IconButton(onClick = onBackClicked) {
                BackIcon(contentDesc = "Go Back")
            }
        },
    )
}

@PortraitLayouts
@Composable
fun PreviewHomeBar() {
    SanTanScanTheme() {
        HomeAppBar(scanning = true, {}, {}, {}, true)
    }
}


@PortraitLayouts
@Composable
fun PreviewAppBar(
    @PreviewParameter(PortraitLayoutParams::class) featureParams: FeatureParams
) {
    val device = ScannedDevice(
        0, "ELK-BLEDOM", "24:A9:30:53:5A:97", -45,
        "Microsoft", listOf("Human Readable Device"),
        listOf("Windows 10 Desktop"), 0L,
        customName = null,
        baseRssi = 0, favorite = false, forget = false
    )
    SanTanScanTheme() {
        AppBarWithBackButton(
            appLayoutInfo = featureParams.appLayoutInfo,
            onBackClicked = { /*TODO*/ },
            scanUi = featureParams.scanState.scanUI,
            deviceDetail = featureParams.detail,
            deviceEvents = featureParams.scanState.deviceEvents,
            bleConnectEvents = featureParams.scanState.bleConnectEvents,
            onControlClick = {}
        )
    }
}

@LandscapeLayouts
@Composable
fun PreviewLandscapeAppBar(
    @PreviewParameter(LandscapeLayoutParams::class) featureParams: FeatureParams
) {
    val device = ScannedDevice(
        0, "ELK-BLEDOM", "24:A9:30:53:5A:97", -45,
        "Microsoft", listOf("Human Readable Device"),
        listOf("Windows 10 Desktop"), 0L,
        customName = null,
        baseRssi = 0, favorite = false, forget = false
    )
    SanTanScanTheme() {
        AppBarWithBackButton(
            appLayoutInfo = featureParams.appLayoutInfo,
            onBackClicked = { /*TODO*/ },
            scanUi = featureParams.scanState.scanUI,
            deviceDetail = featureParams.detail,
            deviceEvents = featureParams.scanState.deviceEvents,
            bleConnectEvents = featureParams.scanState.bleConnectEvents,
            onControlClick = {}
        )
    }
}


