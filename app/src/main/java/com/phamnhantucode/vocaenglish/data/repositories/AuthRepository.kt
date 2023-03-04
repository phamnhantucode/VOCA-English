package com.phamnhantucode.vocaenglish.data.repositories

import android.net.wifi.hotspot2.pps.Credential
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.phamnhantucode.vocaenglish.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>

    suspend fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>

    suspend fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>

}