package com.phamnhantucode.vocaenglish.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phamnhantucode.vocaenglish.ui.activities.HomeActivity
import com.phamnhantucode.vocaenglish.ui.activities.MainScreen
import com.phamnhantucode.vocaenglish.ui.presentation.*

@Composable
fun AuthActivityGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screens.WelcomeScreen.route) {
        composable(Screens.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screens.AuthScreen.route) {
            AuthScreen(navController = navController)
        }
    }
}
@Composable
fun HomeActivityGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(Screens.HomeScreen.route) {
            HomeScreen()
        }
        composable(Screens.WorldScreen.route) {
            WorldScreen()
        }
        composable(Screens.StudyScreen.route) {
            StudyScreen()
        }
        composable(Screens.AccountScreen.route) {
            AccountScreen()
        }
        composable(Screens.DictionaryScreen.route) {
            DictionaryScreen(navController = navController)
        }
    }
}