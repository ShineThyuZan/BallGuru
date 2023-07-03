package com.po.ballguru.ui.theme.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.po.ballguru.R
import com.po.ballguru.ui.theme.common.VerticalSpacerBase3x
import com.po.ballguru.ui.theme.graph.Routes
import com.po.ballguru.ui.theme.resources.dimen
import kotlinx.coroutines.delay

@Composable
fun SplashAnimatedScreen(navController: NavController) {
    val scale = remember {
        Animatable(3f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1000L)
        navController.popBackStack()
        navController.navigate(Routes.HOME)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(MaterialTheme.dimen.base_8x)
                        .scale(scale = scale.value),
                    painter = painterResource(id = R.drawable.football),
                    contentDescription = "Logo Icon",
                )
                VerticalSpacerBase3x()
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.scale(scale = scale.value)
                )

            }

        }
    }
}