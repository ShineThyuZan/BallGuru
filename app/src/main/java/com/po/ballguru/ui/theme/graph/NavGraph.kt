package com.po.ballguru.ui.theme.graph

import android.annotation.SuppressLint
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.po.ballguru.ui.theme.screen.about.AboutScreen
import com.po.ballguru.ui.theme.screen.home.HomeScreen
import com.po.ballguru.ui.theme.screen.news.NewsScreen
import com.po.ballguru.ui.theme.viewmodel.MatchInfoViewModel
import com.po.ballguru.ui.theme.viewmodel.NewsInfoViewModel
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
            NewsScreen(navController = navController,
            viewModel = NewsInfoViewModel()
            )
        }

        composable(
            route = Destination.About.route,
        ) {
            AboutScreen(navController = navController)
        }
    }
}