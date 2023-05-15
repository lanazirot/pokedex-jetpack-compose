package com.lanazirot.pokedex.ui.screens.login


import android.app.Activity.RESULT_OK
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ehsanmsz.mszprogressindicator.progressindicator.TriangleSkewSpinProgressIndicator
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider.getCredential
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.domain.models.common.Response
import com.lanazirot.pokedex.ui.common.components.google.auth.GoogleSignInButton
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.providers.GlobalProvider

@Composable
fun LoginScreen() {

    val loginViewModel: LoginViewModel = hiltViewModel()
    val context = androidx.compose.ui.platform.LocalContext.current
    val googleToken = stringResource(id = R.string.firebase_web_client_id)
    val navigator = GlobalProvider.current.navigation

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

            try {
                val account =
                    task.getResult(ApiException::class.java)!!
                val credential = getCredential(account.idToken, null)
                loginViewModel.signInWithGoogle(
                    credential = credential,
                    toHome = { navigator.navigate(AppRoutes.User.Profile) }  )
            } catch (ignore: ApiException) {
                Log.d("LoginScreen", ignore.toString())
            }
        }
    }

    val launcher2 = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                val credentials = loginViewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = getCredential(googleIdToken, null)
                loginViewModel.firebaseSignInWithGoogle(googleCredentials)
            } catch (it: ApiException) {
                TODO("Handle error")
            }
        }
    }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher2.launch(intent)
    }

    when (val result = loginViewModel.oneTapSignInResult) {
        is Response.Loading -> {
            TriangleSkewSpinProgressIndicator()
        }
        is Response.Success -> result.data?.let { signInResult ->
            LaunchedEffect(signInResult){
                launch(signInResult)
            }
        }
        is Response.Failure -> {
            Log.d("LoginScreen", "Error: ${result.e.message}")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.app_background),
                contentScale = ContentScale.Crop,
                alpha = 0.7f
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.guessmon),
                contentDescription = "Pokeball",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(50.dp)) // profile a login
            GoogleSignInButton(
                text = "Entrar",
                onClick = {
                    val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(googleToken)
                        .requestEmail()
                        .build()
                    val client = GoogleSignIn.getClient(context, options)
                    launcher.launch(client.signInIntent)
                }
            )



        }
    }



}


