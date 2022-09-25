package com.example.bookreaderapp.navigation

enum class ReaderBookScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    HomeScreen,
    DetailScreen,
    SearchScreen,
    StatsScreen,
    UpdateScreen;

    companion object {
        fun fromRoute(route: String): ReaderBookScreens
        = when(route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                LoginScreen.name -> LoginScreen
                CreateAccountScreen.name -> CreateAccountScreen
                HomeScreen.name -> HomeScreen
                DetailScreen.name -> DetailScreen
                SearchScreen.name -> SearchScreen
                StatsScreen.name -> StatsScreen
                UpdateScreen.name -> UpdateScreen
                null -> HomeScreen
            else -> throw IllegalArgumentException("Unknown route = $route")
        }

    }
}
