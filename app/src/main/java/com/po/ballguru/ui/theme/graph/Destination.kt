package com.po.ballguru.ui.theme.graph

object Routes {
    const val ROOT_ROUTE = "root"
    const val HOME = "matches"
}

sealed class Destination(
    val route: String
) {
    object Splash : Destination(route = "splash_screen")
    object Home : Destination("home")
    object News : Destination("news")
}