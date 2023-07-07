package com.aold.advers.ble

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aold.advers.ble.domain.models.AppDestinations.CONTROL
import com.aold.advers.ble.domain.models.AppDestinations.HOME
import com.aold.advers.ble.domain.models.AppDestinations.SETTINGS
import com.aold.advers.ble.domain.models.AppDestinations.TEST
import com.aold.advers.ble.domain.models.AppNavigationActions
import com.aold.advers.ble.domain.models.AppRoutes.CONTROL_SCREEN
import com.aold.advers.ble.domain.models.AppRoutes.HELP_ABOUT
import com.aold.advers.ble.presentation.components.drawer.AppDrawer
import com.aold.advers.ble.presentation.control.ControlScreen
import com.aold.advers.ble.presentation.help.AboutScreen
import com.aold.advers.ble.presentation.scan.HomeRoute
import com.aold.advers.ble.presentation.settings.Settings
import com.aold.advers.ble.presentation.test.TestScreen
import com.aold.advers.ble.presentation.timers.TimerScreen
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(
    startDestination: String,
    appLayoutInfo: AppLayoutInfo,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: HOME
    val navigationActions = remember(navController) {
        AppNavigationActions(navController)
    }
    //ЭКРАН ДЛЯ ТЕСТОВ

//        composable(HOME) {
//            TestScreen(
//                appLayoutInfo = appLayoutInfo,
//                onBackClicked = { navController.popBackStack() })
//        }


    ModalNavigationDrawer(drawerContent = {
        AppDrawer(
            route = currentRoute,
            navigateToHome = { navigationActions.navigateToHome() },
            navigateToSettings = { navigationActions.navigateToSettings() },
            closeDrawer = { coroutineScope.launch { drawerState.close() } },
            modifier = Modifier
        )
    }, drawerState = drawerState) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = currentRoute) },
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }, content = {
                            Icon(
                                imageVector = Icons.Default.Menu, contentDescription = null
                            )
                        })
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                )
            }, modifier = Modifier
        ) {
            NavHost(
                navController = navController,
                startDestination = HOME,
                modifier = modifier.padding(it)
            ) {
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
            }
        }
    }
}
