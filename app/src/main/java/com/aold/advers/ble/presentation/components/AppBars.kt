package com.aold.advers.ble.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import com.aold.advers.ble.presentation.previewparams.FeatureParams
import com.aold.advers.ble.presentation.previewparams.LandscapeLayoutParams
import com.aold.advers.ble.presentation.previewparams.PortraitLayoutParams
import com.aold.advers.ble.presentation.scan.device.DeviceButtons
import com.aold.advers.ble.presentation.scan.device.DeviceMenu
import com.aold.advers.ble.presentation.theme.AdversBleTheme
import com.aold.advers.ble.presentation.theme.appBarTitle
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo
import com.aold.advers.ble.presentation.previewparams.LandscapeLayouts
import com.aold.advers.ble.presentation.previewparams.PortraitLayouts



@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBarWithDrawer(
    onNavigationIconClick: () -> Unit,
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
            containerColor = Color(0xff6ea0c3),
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
                Image(
                    modifier = Modifier.width(120.dp).height(100.dp),
                    painter = painterResource(id = R.drawable.corporation),
                    contentDescription = "Адверс")
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawer"
                )
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
            containerColor = Color.White,
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
                Image(
                    modifier = Modifier.width(120.dp).height(100.dp),
                    painter = painterResource(id = R.drawable.corporation),
                    contentDescription = "Адверс")
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
fun HomeAppBarNew(
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
            containerColor = Color(0xff6ea0c3),
            titleContentColor = Color(0xFFcaccd9),
            navigationIconContentColor = androidx.compose.material3.MaterialTheme.colorScheme
                .onPrimary.copy(.7f)
        ),
        navigationIcon = {
            MainMenu(
                expanded = homeMenuExpanded,
                onExpanded = { homeMenuExpanded = it },
                onHelp = onHelp,
                )
        },
        title = {
            androidx.compose.material3.Text(
                text = if (scanning) "Autoterm" else "Поиск",
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
fun HomeAppBar(
    scanning: Boolean,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onHelp: () -> Unit,
    permissionsGranted: Boolean
) {

    var homeMenuExpanded by rememberSaveable { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        //modifier = Modifier.border(2.dp, Color.Blue),
        windowInsets = WindowInsets(
            top = 0.dp,
            bottom = 0.dp
        ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color(0xFFcaccd9),
            navigationIconContentColor = androidx.compose.material3.MaterialTheme.colorScheme
                .onPrimary.copy(.7f)
        ),
        navigationIcon = {
            MainMenu(
                expanded = homeMenuExpanded,
                onExpanded = { homeMenuExpanded = it },
                onHelp = onHelp,

                )
        },
        title = {
            Spacer(modifier = Modifier.width(30.dp))
            Image(
                modifier = Modifier.width(120.dp).height(100.dp),
                painter = painterResource(id = R.drawable.corporation),
                contentDescription = "Адверс")
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
            containerColor = Color.White,
            titleContentColor = Color.Black,
            navigationIconContentColor = MaterialTheme.colorScheme.primary
        ),
        title = { titleContent() },
        navigationIcon = {
            androidx.compose.material3.IconButton(onClick = onBackClicked) {
                BackIcon(contentDesc = "Go Back")
            }
        },
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BasicBackTopAppBarWithImage(
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
            containerColor = Color.White,
            titleContentColor = Color(0xFFcaccd9),
            navigationIconContentColor = MaterialTheme.colorScheme.primary
        ),
        title = { titleContent() },
        navigationIcon = {
            androidx.compose.material3.IconButton(onClick = onBackClicked) {
                BackIcon(contentDesc = "Go Back")
            }
        },
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BasicBackTopAppBarWithoutBack(
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
            containerColor = Color(0xff6ea0c3),
            titleContentColor = Color(0xFFcaccd9),
            navigationIconContentColor = MaterialTheme.colorScheme.primary
        ),
        title = { titleContent() },
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarWithCentralImage(
    appLayoutInfo: AppLayoutInfo,
    onBackClicked: () -> Unit,
    titleContent: @Composable () -> Unit
) {

    CenterAlignedTopAppBar(
        //modifier = Modifier.border(2.dp, Color.Blue),
        windowInsets = WindowInsets(
            top = 50.dp,
            bottom = 0.dp
        ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color(0xFFcaccd9),
            navigationIconContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Image(
                modifier = Modifier.width(120.dp).height(100.dp),
                painter = painterResource(id = R.drawable.corporation),
                contentDescription = "Адверс")
        },
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarWithCentralImageAndDrawer(
    onNavigationIconClick: () -> Unit,
    appLayoutInfo: AppLayoutInfo,
    onBackClicked: () -> Unit,
    titleContent: @Composable () -> Unit
) {

    CenterAlignedTopAppBar(
        //modifier = Modifier.border(2.dp, Color.Blue),
        windowInsets = WindowInsets(
            top = 50.dp,
            bottom = 0.dp
        ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color(0xFFcaccd9),
            navigationIconContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Image(
                modifier = Modifier.width(120.dp).height(100.dp),
                painter = painterResource(id = R.drawable.corporation),
                contentDescription = "Адверс")
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawer"
                )
            }
        }
    )
}

@PortraitLayouts
@Composable
fun PreviewHomeBar() {
    AdversBleTheme() {
        HomeAppBar(scanning = true, {}, {}, {}, true)
    }
}


@PortraitLayouts
@Composable
fun PreviewAppBar(
    @PreviewParameter(PortraitLayoutParams::class) featureParams: FeatureParams
) {
    val device = ScannedDevice(
        0, "Autoterm", "31:F1:1E:1D:10:86", -45,
        "Advers", listOf("Human Readable Device"),
        listOf("Windows 10 Desktop"), 0L,
        customName = null,
        baseRssi = 0, favorite = false, forget = false
    )
    AdversBleTheme() {
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
        0, "Autoterm", "31:F1:1E:1D:10:86", -45,
        "Microsoft", listOf("Human Readable Device"),
        listOf("Windows 10 Desktop"), 0L,
        customName = null,
        baseRssi = 0, favorite = false, forget = false
    )
    AdversBleTheme() {
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


