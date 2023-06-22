package com.po.ballguru.ui.theme.graph

import android.annotation.SuppressLint
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.po.ballguru.ui.theme.screen.HomeScreen
import com.po.ballguru.ui.theme.screen.NewsScreen
import com.po.ballguru.ui.theme.viewmodel.MatchInfoViewModel


@SuppressLint("UnrememberedGetBackStackEntry")
fun NavGraphBuilder.navGraph(
    navController: NavController
) {
    navigation(
        startDestination = Destination.Home.route,
        route = Routes.HOME
    ) {
        composable(route = Destination.Home.route) {
            HomeScreen(
                navController = navController,
                viewModel = MatchInfoViewModel()
            )
        }
        composable(
            route = Destination.News.route,
        ) {
            NewsScreen(navController = navController)
        }
    }
}