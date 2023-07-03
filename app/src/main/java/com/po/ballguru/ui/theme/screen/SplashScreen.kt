package com.po.ballguru.ui.theme.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.po.ballguru.ui.theme.graph.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
) {
    var logoAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (logoAnimation) 0f else 1f,
        animationSpec = tween(
            durationMillis = 2000
        )
    )
    LaunchedEffect(key1 = true) {
        logoAnimation = true
        delay(1000)
        navController.popBackStack()
        navController.navigate(Routes.HOME)
    }
    SplashScreenContent(alpha = alphaAnimation.value)
}

@Composable
@Preview
private fun Preview() {
    SplashScreenContent(alpha = 1f)
}
