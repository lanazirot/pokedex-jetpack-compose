package com.lanazirot.pokedex.ui.screens.pokedex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lanazirot.pokedex.domain.implementations.PokemonLocalRepository
import com.lanazirot.pokedex.domain.interfaces.IPokemonLocalRepository
import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.ui.providers.GlobalUserProvider
import com.lanazirot.pokedex.ui.providers.UserProvider
import com.lanazirot.pokedex.ui.screens.game.GameViewModel
import com.lanazirot.pokedex.ui.screens.pokedex.components.PokedexList
import com.lanazirot.pokedex.ui.screens.user.UserViewModel

@Composable
fun PokedexScreen(
) {
    val currentUser = GlobalUserProvider.current.currentUser
    var allPokemon by remember { mutableStateOf<List<Pokemon>>(listOf()) }
    val pokemonFounded = currentUser.getPokemonFound()

    LaunchedEffect(Unit) {
        allPokemon = currentUser.getAllPokemonList()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        PokedexList(allPokemon = allPokemon, pokemonFounded = pokemonFounded)
    }
}