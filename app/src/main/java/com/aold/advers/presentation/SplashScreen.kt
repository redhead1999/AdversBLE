package com.aold.advers.presentation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.aold.advers.R
import kotlinx.coroutines.delay

/**
 * @author Kirilin Yury on 09.06.2023.
 */
@Composable
fun SplashScreen(
    navController: NavController,
    onPopBackStack: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
) {
    //todo анимашка
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 300,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(400L)
        navController.navigate("start_home_screen"){
            popUpTo(0)
        }

    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        //todo картинка для сплэша
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Адверс",
            modifier = Modifier.scale(scale.value))
    }
}