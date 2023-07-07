package com.aold.advers.ble.presentation.scan

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aold.advers.ble.domain.models.ScanFilterOption
import com.aold.advers.ble.domain.models.ScanState
import com.aold.advers.ble.presentation.components.AppBarWithBackButton
import com.aold.advers.ble.presentation.components.HomeAppBar
import com.aold.advers.ble.presentation.components.ShowPermissions
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.aold.advers.ble.utils.permissionsList
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(
    vm: ScanViewModel = koinViewModel(),
    onControlClick: (String) -> Unit,
    appLayoutInfo: AppLayoutInfo,
    onHelpClicked: () -> Unit,
    onSettingsClicked: () -> Unit
) {

    val scanState = vm.scanState.collectAsStateWithLifecycle().value
    val scannerMessage = vm.scannerMessage.collectAsStateWithLifecycle().value
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions = permissionsList)
    val isScanning = vm.isScanning.collectAsStateWithLifecycle().value
    val isEditing = vm.isEditing.collectAsStateWithLifecycle().value
    
    BackHandler {
        if (scanState.scanUI.selectedDevice != null) run {
            vm.onBackFromDevice()
        }
    }

    LaunchedEffect(multiplePermissionsState.allPermissionsGranted) {
        if (multiplePermissionsState.allPermissionsGranted) {
            vm.startScan()
        } else
            vm.stopScan()
    }

    val appSnackBarHostState = remember { SnackbarHostState() }
    scanState.scanUI.userMessage?.let { userMessage ->
        LaunchedEffect(scanState.scanUI.userMessage, userMessage) {
            appSnackBarHostState.showSnackbar(userMessage)
            vm.userMessageShown()
        }
    }

    scannerMessage?.let { scanningMessage ->
        LaunchedEffect(scanningMessage) {
            appSnackBarHostState.showSnackbar(scanningMessage)
            vm.scannerMessageShown()
        }
    }

    HomeLayout(
        appLayoutInfo = appLayoutInfo,
        scanState = scanState,
        multiplePermissionsState = multiplePermissionsState,
        appSnackBarHostState = appSnackBarHostState,
        isScanning = isScanning,
        isEditing = isEditing,
        startScan = vm::startScan,
        stopScan = vm::stopScan,
        onControlClick = onControlClick,
        onFilter = vm::onFilter,
        onShowUserMessage = vm::showUserMessage,
        onHelpClicked = onHelpClicked,
        onSettingsClicked = onSettingsClicked
    )

}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeLayout(
    appLayoutInfo: AppLayoutInfo,
    scanState: ScanState,
    multiplePermissionsState: MultiplePermissionsState,
    appSnackBarHostState: SnackbarHostState,
    isScanning: Boolean,
    isEditing: Boolean,
    startScan: () -> Unit,
    stopScan: () -> Unit,
    onControlClick: (String) -> Unit,
    onFilter: (ScanFilterOption?) -> Unit,
    onShowUserMessage: (String) -> Unit,
    onHelpClicked: () -> Unit,
    onSettingsClicked: () -> Unit
) {

    val selectedDevice = scanState.scanUI.selectedDevice


    Scaffold(
        // modifier = Modifier.border(2.dp, Color.Magenta),
        containerColor = Color.Transparent,
        snackbarHost = { SnackbarHost(hostState = appSnackBarHostState) },
        topBar = {
            if (selectedDevice == null)
                HomeAppBar(
                    scanning = isScanning,
                    onStartScan = startScan,
                    onStopScan = stopScan,
                    onHelp = onHelpClicked,
                    permissionsGranted = multiplePermissionsState.allPermissionsGranted
                )
            else
                if (!appLayoutInfo.appLayoutMode.isLandscape())
                    AppBarWithBackButton(
                        appLayoutInfo = appLayoutInfo,
                        onBackClicked = scanState.deviceEvents.onBack,
                        scanUi = scanState.scanUI,
                        deviceDetail = selectedDevice,
                        deviceEvents = scanState.deviceEvents,
                        bleConnectEvents = scanState.bleConnectEvents,
                        onControlClick = onControlClick
                    )
        }
    ) { padding ->

        if (!multiplePermissionsState.allPermissionsGranted) {
            ShowPermissions(
                appLayoutInfo = appLayoutInfo,
                paddingValues = padding,
                multiplePermissionsState = multiplePermissionsState,
                onAboutClick = onHelpClicked
            )
        } else {
            if (scanState.scanUI.selectedDevice == null) {
                DeviceListScreen(
                    devices = scanState.scanUI.devices,
                    onClick = scanState.bleConnectEvents.onConnect,
                    paddingValues = padding,
                    onFilter = onFilter,
                    scanFilterOption = scanState.scanUI.scanFilterOption,
                    onFavorite = scanState.deviceEvents.onFavorite,
                    onForget = scanState.deviceEvents.onForget,
                    appLayoutInfo = appLayoutInfo
                )
            } else {

                val homePadding = if (appLayoutInfo.appLayoutMode.isLandscape()) 0.dp
                else
                    padding.calculateTopPadding()

                Column(
                    modifier = Modifier
                        .padding(top = homePadding)
                        .fillMaxSize()
                    //.padding(horizontal = 8.dp)
                ) {
                    HomeScreen(
                        appLayoutInfo = appLayoutInfo,
                        scanState = scanState,
                        onControlClick = onControlClick,
                        onShowUserMessage = onShowUserMessage,
                        deviceEvents = scanState.deviceEvents,
                        isEditing = isEditing,
                        onBackClicked = scanState.deviceEvents.onBack,
                        onSave = scanState.deviceEvents.onSave
                    )

                }

            }
        }
    }
}

