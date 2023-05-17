package com.lanazirot.pokedex.ui.screens.completed

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lanazirot.pokedex.converters.toFormattedTime
import com.lanazirot.pokedex.ui.common.components.BlackButtonWithPokeball
import com.lanazirot.pokedex.ui.common.components.country.CustomInputDropdownStates
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.providers.GlobalProvider
import com.lanazirot.pokedex.ui.screens.pokedex.components.PokemonHeaderLabel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CompletedScreen() {
    val completedViewModel: CompletedViewModel = hiltViewModel()

    val navController = GlobalProvider.current.navigation
    val user = completedViewModel.user.collectAsState().value

    val status = completedViewModel.status.collectAsState().value

    BoxWithConstraints {
        Scaffold(
            content = {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        PokemonHeaderLabel(text = "Felicidades!!")
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Ya eres un maestro pokemon",
                            fontSize = 16.sp,
                            color = Color(0xFF4171CA)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Completa tus datos:")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        GlideImage(
                            model = user.photoUrl,
                            contentDescription = "User image",
                            modifier = Modifier.size(100.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CustomInputDropdownStates(
                            value = user.country,
                            onValueChange = { newValue ->
                                completedViewModel.setCountry(newValue)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = user.name)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Numero de intentos: ${user.currentUserData.attempts}")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Tiempo jugado: ${user.currentUserData.playedTime.toFormattedTime()}")
                    }
                }
            },
            bottomBar = {
                BlackButtonWithPokeball(
                    text = "Guardar",
                    route = "",
                    popUpTo = "",
                    ownClick = true,
                    color = Color.Black,
                    enabled = status.isValid,
                    onClick = {
                        completedViewModel.saveUserInLeaderboard()
                        navController.navigate(AppRoutes.User.Profile)
                    }
                )
            },
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }

}