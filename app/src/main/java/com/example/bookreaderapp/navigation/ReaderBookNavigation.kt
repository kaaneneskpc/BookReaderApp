package com.example.bookreaderapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookreaderapp.ui.screen.detailScreen.ReaderBookDetailScreen
import com.example.bookreaderapp.ui.screen.homeScreen.ReaderBookHomeScreen
import com.example.bookreaderapp.ui.screen.homeScreen.ReaderBookHomeScreenViewModel
import com.example.bookreaderapp.ui.screen.loginScreen.ReaderBookLoginScreen
import com.example.bookreaderapp.ui.screen.searchScreen.ReaderBookSearchScreen
import com.example.bookreaderapp.ui.screen.searchScreen.ReaderBookSearchScreenViewModel
import com.example.bookreaderapp.ui.screen.splashScreen.ReaderBookSplashScreen
import com.example.bookreaderapp.ui.screen.statsScreen.ReaderBookStatsScreen
import com.example.bookreaderapp.ui.screen.updateScreen.ReaderBookUpdateScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ReaderBookNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderBookScreens.SplashScreen.name) {
        composable(ReaderBookScreens.SplashScreen.name) {
            ReaderBookSplashScreen(navController = navController)
        }
        composable(ReaderBookScreens.HomeScreen.name) {
            val homeScreenViewModel = hiltViewModel<ReaderBookHomeScreenViewModel>()
            ReaderBookHomeScreen(navController = navController, viewModel = homeScreenViewModel)
        }
        val detailName = ReaderBookScreens.DetailScreen.name
        composable("$detailName/{bookId}", arguments = listOf(navArgument("bookId") {
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookId").let {
                ReaderBookDetailScreen(navController = navController, bookId = it.toString())
            }
        }

        val updateName = ReaderBookScreens.UpdateScreen.name
        composable("$updateName/{bookItemId}", arguments = listOf(navArgument("bookItemId") {
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookItemId").let {
                ReaderBookUpdateScreen(navController = navController, bookItemId = it.toString())
            }
        }
        composable(ReaderBookScreens.LoginScreen.name) {
            ReaderBookLoginScreen(navController = navController)
        }
        composable(ReaderBookScreens.CreateAccountScreen.name) {
            ReaderBookSplashScreen(navController = navController)
        }
        composable(ReaderBookScreens.SearchScreen.name) {
            val searchScreenViewModel = hiltViewModel<ReaderBookSearchScreenViewModel>()
            ReaderBookSearchScreen(navController = navController, viewModel = searchScreenViewModel)
        }
        composable(ReaderBookScreens.StatsScreen.name) {
            ReaderBookStatsScreen(navController = navController)
        }
    }
}