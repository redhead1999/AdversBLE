package com.aold.advers.ble.presentation.new.previewparams

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.aold.advers.ble.presentation.new.scan.HomeLayout
import com.aold.advers.ble.presentation.new.theme.SanTanScanTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState

//@OptIn(ExperimentalPermissionsApi::class)
//@PortraitLayouts
//@Composable
//fun HomeLayoutPreview(
//    @PreviewParameter(PortraitLayoutParams::class) featureParams: FeatureParams
//) {
//
//    SanTanScanTheme() {
//        Column {
//            HomeLayout(
//                appLayoutInfo = featureParams.appLayoutInfo,
//                scanState = featureParams.scanState,
//                multiplePermissionsState = object : MultiplePermissionsState {
//                    override val allPermissionsGranted: Boolean
//                        get() = true
//                    override val permissions: List<PermissionState>
//                        get() = TODO("Not yet implemented")
//                    override val revokedPermissions: List<PermissionState>
//                        get() = TODO("Not yet implemented")
//                    override val shouldShowRationale: Boolean
//                        get() = TODO("Not yet implemented")
//
//                    override fun launchMultiplePermissionRequest() {
//                        TODO("Not yet implemented")
//                    }
//                },
//                appSnackBarHostState = remember { SnackbarHostState() },
//                isScanning = true,
//                isEditing = false,
//                startScan = { /*TODO*/ },
//                stopScan = { /*TODO*/ },
//                onControlClick = {},
//                onFilter = {},
//                onShowUserMessage = {},
//                onHelpClicked = {}
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalPermissionsApi::class)
//@PortraitNarrowLayouts
//@Composable
//fun HomeLayoutNarrowPreview(
//    @PreviewParameter(PortraitNarrowLayoutParams::class) featureParams: FeatureParams
//) {
//
//    SanTanScanTheme() {
//        Column {
//            HomeLayout(
//                appLayoutInfo = featureParams.appLayoutInfo,
//                scanState = featureParams.scanState,
//                multiplePermissionsState = object : MultiplePermissionsState {
//                    override val allPermissionsGranted: Boolean
//                        get() = true
//                    override val permissions: List<PermissionState>
//                        get() = TODO("Not yet implemented")
//                    override val revokedPermissions: List<PermissionState>
//                        get() = TODO("Not yet implemented")
//                    override val shouldShowRationale: Boolean
//                        get() = TODO("Not yet implemented")
//
//                    override fun launchMultiplePermissionRequest() {
//                        TODO("Not yet implemented")
//                    }
//                },
//                appSnackBarHostState = remember { SnackbarHostState() },
//                isScanning = true,
//                isEditing = false,
//                startScan = { /*TODO*/ },
//                stopScan = { /*TODO*/ },
//                onControlClick = {},
//                onFilter = {},
//                onShowUserMessage = {},
//                onHelpClicked = {}
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalPermissionsApi::class)
//@LandscapeLayouts
//@Composable
//fun HomeLandscapeLayoutPreview(
//    @PreviewParameter(LandscapeLayoutParams::class) featureParams: FeatureParams
//) {
//
//    SanTanScanTheme() {
//        Column {
//            HomeLayout(
//                appLayoutInfo = featureParams.appLayoutInfo,
//                scanState = featureParams.scanState,
//                multiplePermissionsState = object : MultiplePermissionsState {
//                    override val allPermissionsGranted: Boolean
//                        get() = true
//                    override val permissions: List<PermissionState>
//                        get() = TODO("Not yet implemented")
//                    override val revokedPermissions: List<PermissionState>
//                        get() = TODO("Not yet implemented")
//                    override val shouldShowRationale: Boolean
//                        get() = TODO("Not yet implemented")
//
//                    override fun launchMultiplePermissionRequest() {
//                        TODO("Not yet implemented")
//                    }
//                },
//                appSnackBarHostState = remember { SnackbarHostState() },
//                isScanning = true,
//                isEditing = false,
//                startScan = { /*TODO*/ },
//                stopScan = { /*TODO*/ },
//                onControlClick = {},
//                onFilter = {},
//                onShowUserMessage = {},
//                onHelpClicked = {}
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalPermissionsApi::class)
//@LandscapeBig
//@Composable
//fun HomeLayoutBigPreview(
//    @PreviewParameter(LandscapeBigLayoutParams::class) featureParams: FeatureParams
//) {
//
//    SanTanScanTheme() {
//        Column {
//            HomeLayout(
//                appLayoutInfo = featureParams.appLayoutInfo,
//                scanState = featureParams.scanState,
//                multiplePermissionsState = object : MultiplePermissionsState {
//                    override val allPermissionsGranted: Boolean
//                        get() = true
//                    override val permissionRequested: Boolean
//                        get() = TODO("Not yet implemented")
//                    override val permissions: List<PermissionState>
//                        get() = TODO("Not yet implemented")
//                    override val revokedPermissions: List<PermissionState>
//                        get() = TODO("Not yet implemented")
//                    override val shouldShowRationale: Boolean
//                        get() = TODO("Not yet implemented")
//
//                    override fun launchMultiplePermissionRequest() {
//                        TODO("Not yet implemented")
//                    }
//                },
//                appSnackBarHostState = remember { SnackbarHostState() },
//                isScanning = true,
//                isEditing = false,
//                startScan = { /*TODO*/ },
//                stopScan = { /*TODO*/ },
//                onControlClick = {},
//                onFilter = {},
//                onShowUserMessage = {},
//                onHelpClicked = {}
//            )
//        }
//    }
//}
