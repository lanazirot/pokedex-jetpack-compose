package com.lanazirot.pokedex.ui.screens.pokedex.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import com.lanazirot.pokedex.domain.models.Pokemon

@Composable
fun PokedexList(allPokemon: List<Pokemon>, pokemonFounded: List<Pokemon>) {
    LazyVerticalGrid (
        columns = GridCells.Fixed(2)
    ) {
        items(allPokemon.size) {
            val pokemon = allPokemon[it]
            PokemonSimpleCard(pokemon = pokemon, isVisible = pokemonFounded.contains(pokemon))
        }
    }
}
