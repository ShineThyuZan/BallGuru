package com.po.ballguru.ui.theme.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.po.ballguru.ui.theme.graph.RootNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            RootNavGraph(navController = navController)
        }
    }
}