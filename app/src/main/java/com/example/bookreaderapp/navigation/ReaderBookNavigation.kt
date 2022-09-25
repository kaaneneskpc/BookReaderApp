package com.example.bookreaderapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookreaderapp.ui.screen.detailScreen.ReaderBookDetailScreen
import com.example.bookreaderapp.ui.screen.homeScreen.ReaderBookHomeScreen
import com.example.bookreaderapp.ui.screen.loginScreen.ReaderBookLoginScreen
import com.example.bookreaderapp.ui.screen.searchScreen.ReaderBookSearchScreen
import com.example.bookreaderapp.ui.screen.splashScreen.ReaderBookSplashScreen
import com.example.bookreaderapp.ui.screen.statsScreen.ReaderBookStatsScreen
import com.example.bookreaderapp.ui.screen.updateScreen.ReaderBookUpdateScreen

@Composable
fun ReaderBookNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderBookScreens.SplashScreen.name) {
        composable(ReaderBookScreens.SplashScreen.name) {
            ReaderBookSplashScreen(navController = navController)
        }
        composable(ReaderBookScreens.HomeScreen.name) {
            ReaderBookHomeScreen(navController = navController)
        }
        composable(ReaderBookScreens.DetailScreen.name) {
            ReaderBookDetailScreen(navController = navController)
        }
        composable(ReaderBookScreens.LoginScreen.name) {
            ReaderBookLoginScreen(navController = navController)
        }
        composable(ReaderBookScreens.CreateAccountScreen.name) {
            ReaderBookSplashScreen(navController = navController)
        }
        composable(ReaderBookScreens.SearchScreen.name) {
            ReaderBookSearchScreen(navController = navController)
        }
        composable(ReaderBookScreens.StatsScreen.name) {
            ReaderBookStatsScreen(navController = navController)
        }
        composable(ReaderBookScreens.UpdateScreen.name) {
            ReaderBookUpdateScreen(navController = navController)
        }
    }
}