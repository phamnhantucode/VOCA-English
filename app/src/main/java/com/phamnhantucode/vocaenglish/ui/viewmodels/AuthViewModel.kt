package com.phamnhantucode.vocaenglish.ui.viewmodels

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor()
    : ViewModel() {

    private val min_password = 6

    val isEmailValid = mutableStateOf(true)
    val isPasswordValid = mutableStateOf(true)
    val isEmailOrPasswordEmpty = mutableStateOf(false)

    fun checkEmailAndPasswordValid(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            isEmailOrPasswordEmpty.value = true
            return
        }
        isEmailOrPasswordEmpty.value = false
        isEmailValid.value = !Patterns.EMAIL_ADDRESS.matcher(email).matches().not()
        isPasswordValid.value = password.length >= min_password
    }
}