package com.aold.advers

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.aold.advers.ble.AppNavGraph
import com.aold.advers.ble.domain.models.AppRoutes.HOME_SCREEN
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BleApp(
    appLayoutInfo: AppLayoutInfo
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val backgroundImage = if (isSystemInDarkTheme())
            painterResource(id = R.drawable.ble_background_dark)
        else
            painterResource(id = R.drawable.ble_background)

        if (!isSystemInDarkTheme()) {
            Image(
                painter = backgroundImage,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )
        }

        val backgroundColor = if (!isSystemInDarkTheme())
            Color.Transparent
        else
            MaterialTheme.colorScheme.tertiary

        Surface(
            color = backgroundColor,
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .fillMaxSize()
        ) {
            AppNavGraph(
                navController = rememberNavController(),
                startDestination = HOME_SCREEN, appLayoutInfo = appLayoutInfo
            )
        }
    }

}