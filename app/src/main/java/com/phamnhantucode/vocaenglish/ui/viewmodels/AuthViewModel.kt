package com.phamnhantucode.vocaenglish.ui.viewmodels

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.phamnhantucode.vocaenglish.Helper
import com.phamnhantucode.vocaenglish.data.repositories.AuthRepository
import com.phamnhantucode.vocaenglish.ui.presentation.auth_screen.AuthState
import com.phamnhantucode.vocaenglish.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    val authRepository: AuthRepository,
    val firebaseAuth: FirebaseAuth
) : ViewModel() {

    val isNetworkAvailable = mutableStateOf(
        false
    )

    val _authState = Channel<AuthState>()
    val authState = _authState.receiveAsFlow()

    private val min_password = 6
    private val auth = Firebase.auth

    val isEmailValid = mutableStateOf(true)
    val isPasswordValid = mutableStateOf(true)
    val isEmailOrPasswordEmpty = mutableStateOf(false)
    val isSignUpSuccess = mutableStateOf(false)

    init {
        isNetworkAvailable()
    }
    fun checkEmailAndPasswordValid(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            isEmailOrPasswordEmpty.value = true
            return
        }
        isEmailOrPasswordEmpty.value = false
        isEmailValid.value = !Patterns.EMAIL_ADDRESS.matcher(email).matches().not()
        isPasswordValid.value = password.length >= min_password
        if (isEmailValid.value && isPasswordValid.value && !isEmailOrPasswordEmpty.value)
            registerAndLogin(email, password)
    }

    private fun registerAndLogin(email: String, password: String) {
        viewModelScope.launch {
            isNetworkAvailable()
            if (isNetworkAvailable.value) {
                authRepository.registerUser(email, password).collect {result ->
                    when (result) {
                        is Resource.Success -> {
                            _authState.send(AuthState(success = result.data))
                        }
                        is Resource.Error -> {
                            _authState.send(AuthState(error = result.message.toString()))
                        }
                        is Resource.Loading -> {
                            _authState.send(AuthState(loading = true))
                        }
                    }
                }
//            auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        val user = auth.currentUser
//                        isSignUpSuccess.value = true
//                        Timber.d("Sign in success")
//                    } else {
//                        isSignUpSuccess.value = false
//                        Timber.d("Sign in failure")
//                    }
//                }
            }
        }
    }

    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        authRepository.googleSignIn(credential).collect {result ->
            when (result) {
                is Resource.Success -> {
                    _authState.send(AuthState(success = result.data))
                }
                is Resource.Error -> {
                    _authState.send(AuthState(error = result.message ?: ""))
                }
                is Resource.Loading -> {
                    _authState.send(AuthState(loading = true))
                }
            }
        }
    }

    fun isNetworkAvailable() {
        isNetworkAvailable.value = Helper.isNetworkAvailable(context)
    }

    fun resetAuthState() {
        viewModelScope.launch {
            _authState.send(AuthState())
        }
    }


}