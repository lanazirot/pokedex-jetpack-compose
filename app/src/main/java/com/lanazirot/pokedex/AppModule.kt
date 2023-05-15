package com.lanazirot.pokedex

import android.app.Application
import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.lanazirot.pokedex.domain.constants.DiConstants.SIGN_IN
import com.lanazirot.pokedex.domain.constants.DiConstants.SIGN_UP
import com.lanazirot.pokedex.domain.implementations.auth.AuthService
import com.lanazirot.pokedex.domain.implementations.game.PokemonLocalRepository
import com.lanazirot.pokedex.domain.implementations.game.PokemonRepository
import com.lanazirot.pokedex.domain.implementations.game.UserManager
import com.lanazirot.pokedex.domain.implementations.leaderboard.LeaderboardService
import com.lanazirot.pokedex.domain.interfaces.*
import com.lanazirot.pokedex.domain.interfaces.auth.IAuthService
import com.lanazirot.pokedex.domain.interfaces.game.IPokemonLocalRepository
import com.lanazirot.pokedex.domain.interfaces.game.IPokemonRepository
import com.lanazirot.pokedex.domain.interfaces.game.IPokemonService
import com.lanazirot.pokedex.domain.interfaces.game.IUserManager
import com.lanazirot.pokedex.domain.interfaces.leaderboard.ILeaderBoardService
import com.lanazirot.pokedex.domain.services.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonRepository(): IPokemonRepository = PokemonRepository()

    @Provides
    @Singleton
    fun providePokemonService(): IPokemonService = PokemonService(pokemonRepository = providePokemonRepository())

    @Provides
    @Singleton
    fun providePokemonLocalRepository(): IPokemonLocalRepository = PokemonLocalRepository(pokemonRepository = providePokemonRepository())

    @Provides
    @Singleton
    fun provideUserManager(): IUserManager = UserManager(pokemonLocalRepository = providePokemonLocalRepository(), firebaseAuth = provideFirebaseAuth(), firebaseFirestore = provideFirestore())

    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideFirestore() = Firebase.firestore

    @Provides
    fun provideOneTapClient(@ApplicationContext context: Context) = Identity.getSignInClient(context)

    @Provides
    @Named(SIGN_IN)
    fun provideSignInRequest(app: Application) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.firebase_web_client_id))
                .setFilterByAuthorizedAccounts(true)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()

    @Provides
    @Named(SIGN_UP)
    fun provideSignUpRequest(app: Application) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.firebase_web_client_id))
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()


    @Provides
    fun provideAuthService(auth: FirebaseAuth, oneTapClient: SignInClient, @Named(SIGN_IN) signInRequest: BeginSignInRequest): IAuthService = AuthService(
        auth = auth,
        oneTapClient = oneTapClient,
        signInRequest = signInRequest,
        userManager = provideUserManager()
    )

    @Provides
    fun provideLeaderboardService(): ILeaderBoardService = LeaderboardService(firebaseFirestore = provideFirestore())

    @Provides
    @Singleton
    fun provideGoogleSignInClient(@ApplicationContext applicationContext: Context): GoogleSignInClient {
        val mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(applicationContext.getString(R.string.firebase_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(applicationContext, mGoogleSignInOptions)
    }
}