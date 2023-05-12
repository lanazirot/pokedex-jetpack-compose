package com.lanazirot.pokedex.domain.interfaces.auth

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import com.lanazirot.pokedex.domain.models.common.Response

interface IAuthService {
    val isUserAuthenticatedInFirebase: Boolean
    suspend fun oneTapSignInWithGoogle(): Response<BeginSignInResult>
    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Response<Boolean>
}