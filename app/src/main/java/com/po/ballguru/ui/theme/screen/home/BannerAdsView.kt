package com.po.ballguru.ui.theme.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.po.ballguru.ui.theme.resources.dimen


@Composable
fun BannerAds(
    modifier: Modifier,
    adListener: AdListener? = null
) {
    val deviceCurrentWidth = LocalConfiguration.current.screenWidthDp
    val padding = 1
    var i by remember { mutableStateOf(0) }
    var containerWidth by remember { mutableStateOf<Int?>(null) }
    val context = LocalContext.current
    val adUnitId = "ca-app-pub-6403106238282709/3513120702"
    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .onSizeChanged {
                containerWidth = it.width
            },
        contentAlignment = Alignment.Center
    ) {
        AndroidView(factory = {
            AdView(it)
        }, modifier = modifier, update = {
            if (it.adSize == null)
                it.setAdSize(
                    AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                        context,
                        deviceCurrentWidth - padding * 2
                    )
                )
            try {
                it.adUnitId = adUnitId //Can only be set once
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (adListener != null)
                it.adListener = adListener

            it.loadAd(AdRequest.Builder().build())
        })
    }
}


/*full screen
ca-app-pub-6403106238282709~2000728483


ca-app-pub-6403106238282709/4993723334

banner

ca-app-pub-6403106238282709~2000728483

ca-app-pub-6403106238282709/3513120702

Content

ca-app-pub-6403106238282709~2000728483

ca-app-pub-6403106238282709/3081612532

Interstitial
ca-app-pub-6403106238282709~2000728483

ca-app-pub-6403106238282709/3313848140*/

