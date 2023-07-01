package com.aold.advers.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aold.advers.ble.presentation.BleOperationsActivity
import com.aold.advers.presentation.components.TestScreen



/**
 * @author Kirilin Yury on 09.06.2023.
 */

@RequiresApi(Build.VERSION_CODES.M)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation(
    onBluetoothStateChanged: () -> Unit,
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.StartScreen.route) {
            StartScreen(navController = navController)
        }
        
        composable(Screen.SettingsScreen.route) {
            SettingsScreen(
                navController = navController
            )
        }

        composable(Screen.TimerScreen.route) {
            TimerScreen(
                navController = navController
            )
        }

        composable(Screen.TemperatureChartScreen.route) {
            TemperatureChartScreen(
                navController = navController
            )
        }

        composable(Screen.VoltageChartScreen.route) {
            VoltageChartScreen(
                navController = navController
            )
        }

        composable(Screen.TestScreen.route) {
            TestScreen(
                navController = navController
            )
        }

        composable(Screen.BleOperationsActivity.route) {
            BleOperationsActivity(
            )
        }
    }
}

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")

    object StartScreen : Screen("start_screen")
    object TemperatureHumidityScreen : Screen("temp_humid_screen")
    object SettingsScreen : Screen("settings_screen")
    object TimerScreen : Screen("timer_screen")
    object TemperatureChartScreen : Screen("temperature_chart_screen")
    object VoltageChartScreen : Screen("voltage_chart_screen")
    object TestScreen : Screen("test_screen")
    object BluetoothChatScreen : Screen("bluetooth_chat_screen")
    object BleOperationsActivity : Screen("ble_operations_activity")

}