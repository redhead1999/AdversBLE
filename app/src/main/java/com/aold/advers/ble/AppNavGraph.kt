package com.aold.advers.ble

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aold.advers.ble.domain.models.AppDestinations.CONTROL
import com.aold.advers.ble.domain.models.AppDestinations.HOME
import com.aold.advers.ble.domain.models.AppDestinations.SETTINGS
import com.aold.advers.ble.domain.models.AppDestinations.TEST
import com.aold.advers.ble.domain.models.AppRoutes.CONTROL_SCREEN
import com.aold.advers.ble.domain.models.AppRoutes.HELP_ABOUT
import com.aold.advers.ble.presentation.control.ControlScreen
import com.aold.advers.ble.presentation.help.AboutScreen
import com.aold.advers.ble.presentation.scan.HomeRoute
import com.aold.advers.ble.presentation.settings.Settings
import com.aold.advers.ble.presentation.test.Test
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String,
    appLayoutInfo: AppLayoutInfo,
    openDrawer: () -> Unit = {},
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        //ЭКРАН ДЛЯ ТЕСТОВ

        composable(HOME) {
            HomeRoute(
                onControlClick = { deviceAddress ->
                    navController.navigate("$CONTROL_SCREEN/$deviceAddress")
                },
                appLayoutInfo = appLayoutInfo,
                onHelpClicked = { navController.navigate(HELP_ABOUT) },
                onSettingsClicked = { navController.navigate(SETTINGS) }
            )
        }

        composable(HOME) {
            HomeRoute(
                onControlClick = { deviceAddress ->
                    navController.navigate("$CONTROL_SCREEN/$deviceAddress")
                },
                appLayoutInfo = appLayoutInfo,
                onHelpClicked = { navController.navigate(HELP_ABOUT) },
                onSettingsClicked = { navController.navigate(SETTINGS) }
            )
        }
        composable(CONTROL) {
            ControlScreen(
                appLayoutInfo = appLayoutInfo,
                onBackClicked = { navController.popBackStack() }
            )
        }
        composable(HELP_ABOUT) {
            AboutScreen(
                appLayoutInfo = appLayoutInfo,
                onBackClicked = { navController.popBackStack() }
            )
        }
        composable(SETTINGS) {
            Settings(
                appLayoutInfo = appLayoutInfo,
                onBackClicked = { navController.popBackStack() }
            )
        }
        composable(TEST) {
            Test(
                appLayoutInfo = appLayoutInfo,
                onBackClicked = { navController.popBackStack() }
            )
        }
    }
}
