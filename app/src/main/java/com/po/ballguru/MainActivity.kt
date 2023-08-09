package com.po.ballguru

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.po.ballguru.ui.theme.resources.BallGuruTheme
import com.po.ballguru.ui.theme.screen.MainScreen
import com.po.ballguru.ui.theme.screen.home.InterstitialAdScreen

class MainActivity : ComponentActivity() {
    private lateinit var adView: AdView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        adView = AdView(this)
        adView.setAdSize(AdSize.BANNER)
        adView.adUnitId = "ca-app-pub-6403106238282709/3513120702"

        setContent {
            BallGuruTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MainScreen(navController = navController)
                  // InterstitialAdScreen(activity = this)
                }
            }
        }
    }
}
