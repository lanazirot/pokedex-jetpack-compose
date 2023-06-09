package com.lanazirot.pokedex.ui.screens.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lanazirot.pokedex.domain.interfaces.auth.IAuthService
import com.lanazirot.pokedex.domain.models.common.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authService: IAuthService, val oneTapClient: SignInClient) : ViewModel() {
    val userAuthenticated = authService.isUserAuthenticatedInFirebase

    var oneTapSignInResult by mutableStateOf<Response<BeginSignInResult>>(Response.Success(null))
        private set

    private var firebaseSignInResult by mutableStateOf<Response<Boolean>>(Response.Success(null))


    fun firebaseSignInWithGoogle(credentials: AuthCredential) = viewModelScope.launch {
        firebaseSignInResult = authService.firebaseSignInWithGoogle(credentials)
    }


    fun signInWithGoogle(credential: AuthCredential, toHome: () -> Unit) {
        try {
            Firebase.auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        viewModelScope.launch {
                            authService.checkIfUserIsInFirestore()
                            toHome()
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    throw exception
                }
        } catch (e: Exception) {
            Log.d("LoginViewModel", e.toString())
        }
    }
}
