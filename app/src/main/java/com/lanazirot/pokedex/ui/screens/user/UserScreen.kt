package com.lanazirot.pokedex.ui.screens.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.lanazirot.pokedex.ui.providers.GlobalUserProvider
import com.lanazirot.pokedex.ui.screens.pokedex.components.PokemonSimpleCard

@Composable
fun UserScreen() {
    val currentUser = GlobalUserProvider.current.currentUser

    Column(){
        Text(text = "UserScreen")

        LazyColumn {
            items(currentUser.getPokemonFound()) {
                    p -> PokemonSimpleCard(pokemon = p, isVisible = true)
            }
        }

        Text(text = currentUser.getCountPokemonFound().toString())
        Text(text = currentUser.getAttemptsNumber().toString())
        Text(text = currentUser.getPokedexProgress().toString())

    }

}
