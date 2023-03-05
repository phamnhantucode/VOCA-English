package com.phamnhantucode.vocaenglish.ui.navigations

import androidx.compose.runtime.Composable

sealed class Screens(val route: String) {
    object WelcomeScreen : Screens("wellcome_screen")
    object AuthScreen: Screens("auth_screen")


    object HomeScreen: Screens("home_screen")
    object AccountScreen: Screens("account_screen")
    object StudyScreen: Screens("study_screen")
    object WorldScreen: Screens("world_screen")

    object DictionaryScreen: Screens("dictionary_screen")

}