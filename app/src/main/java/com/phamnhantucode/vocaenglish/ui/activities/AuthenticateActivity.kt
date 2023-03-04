package com.phamnhantucode.vocaenglish.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phamnhantucode.vocaenglish.ui.presentation.AuthScreen
import com.phamnhantucode.vocaenglish.ui.presentation.WelcomeScreen
import com.phamnhantucode.vocaenglish.ui.theme.VOCAEnglishTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticateActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            VOCAEnglishTheme {
                NavHost(navController = navController, startDestination = "welcome_screen") {
                    composable("welcome_screen") {
                        WelcomeScreen(navController)
                    }
                    composable("login_screen") {
                        AuthScreen(navController)
                    }
                }
            }
        }
    }
}