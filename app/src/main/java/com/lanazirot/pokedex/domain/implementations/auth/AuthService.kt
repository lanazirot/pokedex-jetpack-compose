package com.lanazirot.pokedex.domain.implementations.auth

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.lanazirot.pokedex.domain.interfaces.auth.IAuthService
import com.lanazirot.pokedex.domain.models.common.Response
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(private val auth: FirebaseAuth, private var oneTapClient: SignInClient, private var signInRequest: BeginSignInRequest) : IAuthService{
    override val isUserAuthenticatedInFirebase: Boolean = auth.currentUser != null
    override suspend fun oneTapSignInWithGoogle(): Response<BeginSignInResult> {
        return try {
            val result = oneTapClient.beginSignIn(signInRequest).await()
            Response.Success(result)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Response<Boolean> {
        return try {
            auth.signInWithCredential(googleCredential).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}