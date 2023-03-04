package com.phamnhantucode.vocaenglish.ui.presentation.auth_screen

import com.google.firebase.auth.AuthResult

data class AuthState(
    val success: AuthResult? = null,
    val loading: Boolean = false,
    val error: String = ""
)