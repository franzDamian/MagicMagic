package com.magic.magicmagic.navigation

interface Route {
    val route: String
    val title: String
}

object HomeRoute : Route {
    override val route = "Home"
    override val title = "Home"
}

object PlayerRoute : Route {
    override val route = "Player"
    override val title = "Player"
}

val routes = listOf(HomeRoute, PlayerRoute)