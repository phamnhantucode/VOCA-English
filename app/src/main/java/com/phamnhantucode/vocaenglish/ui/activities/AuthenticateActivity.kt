package com.phamnhantucode.vocaenglish.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phamnhantucode.vocaenglish.ui.screens.AuthScreen
import com.phamnhantucode.vocaenglish.ui.screens.WelcomeScreen
import com.phamnhantucode.vocaenglish.ui.theme.VOCAEnglishTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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