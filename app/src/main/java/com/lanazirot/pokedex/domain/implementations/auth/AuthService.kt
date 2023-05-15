package com.lanazirot.pokedex.domain.implementations.auth

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.lanazirot.pokedex.domain.implementations.game.UserManager
import com.lanazirot.pokedex.domain.interfaces.auth.IAuthService
import com.lanazirot.pokedex.domain.interfaces.game.IUserManager
import com.lanazirot.pokedex.domain.models.common.Response
import com.lanazirot.pokedex.domain.models.common.Response.Failure
import com.lanazirot.pokedex.domain.models.common.Response.Success
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    private var signInRequest: BeginSignInRequest,
    private val userManager: IUserManager
) : IAuthService {
    override val isUserAuthenticatedInFirebase: Boolean = auth.currentUser != null
    override suspend fun oneTapSignInWithGoogle(): Response<BeginSignInResult> {
        return try {
            val result = oneTapClient.beginSignIn(signInRequest).await()
            Success(result)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Response<Boolean> {
        return try {
            auth.signInWithCredential(googleCredential).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun checkIfUserIsInFirestore(): Response<Boolean> {
        return try {
            val user = auth.currentUser
            val db = FirebaseFirestore.getInstance()
            val docRef = db.collection("users").document(user!!.email!!)
            val doc = docRef.get().await()
            if (doc.exists()) {
                userManager.onLateInit()
                Success(true)
            } else {
                //Add user to firestore
                val data = hashMapOf(
                    "name" to user.displayName,
                    "email" to user.email,
                    "photoUrl" to user.photoUrl.toString(),
                    "country" to "",
                )
                db.collection("users").document(user.email!!).set(data).await()
                Success(false)
            }
        } catch (e: Exception) {
            Failure(e)
        }
    }
}