package com.po.ballguru.ui.theme.screen.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

@Composable
fun InterstitialAdScreen(activity: ComponentActivity) {
    var interstitialAd by remember { mutableStateOf<InterstitialAd?>(null) }
    var adLoaded by remember { mutableStateOf(false) }

    // Load the interstitial ad
    LaunchedEffect(Unit) {
        InterstitialAd.load(
            activity,
            "ca-app-pub-3940256099942544/1033173712", // Test ad unit ID
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    super.onAdLoaded(ad)
                    interstitialAd = ad
                    adLoaded = true
                }
            }
        )
    }

    MaterialTheme {
        Column {
            if (adLoaded && interstitialAd != null) {
                Button(
                    onClick = {
                        interstitialAd?.show(activity)
                    },
                    content = {
                        Text("Show Interstitial Ad")
                    }
                )
            }
        }
    }
}

