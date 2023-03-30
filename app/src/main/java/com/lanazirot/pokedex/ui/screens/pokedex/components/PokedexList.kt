package com.lanazirot.pokedex.ui.screens.pokedex.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.lanazirot.pokedex.domain.models.Pokemon

@Composable
fun PokedexList(allPokemon: List<Pokemon>, pokemonFounded: List<Pokemon>) {
    LazyColumn {
        items(allPokemon) {
            p -> PokemonSimpleCard(pokemon = p, isVisible = pokemonFounded.contains(p))
        }
    }
}
