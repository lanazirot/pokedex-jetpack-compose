package com.lanazirot.pokedex.ui.screens.pokedex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lanazirot.pokedex.domain.implementations.PokemonLocalRepository
import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.ui.screens.pokedex.components.PokedexList

@Composable
fun PokedexScreen() {
    var allPokemon by remember { mutableStateOf<List<Pokemon>>(listOf()) }

    LaunchedEffect(Unit) {
        allPokemon = PokemonLocalRepository().getPokemonList()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        PokedexList(allPokemon = allPokemon, pokemonFounded = listOf(Pokemon(id = 1, name = "Bulbasaur", type1 = "Grass", type2 = "Poison", total = 318, hp = 45, attack = 49, defense = 49, spAtk = 65, spDef = 65, speed = 45, generation = 1, legendary = "False"), Pokemon( id = 3, name = "Venusaur", type1 = "Grass", type2 = "Poison", total = 525, hp = 80, attack = 82, defense = 83, spAtk = 100, spDef = 100, speed = 80, generation = 1, legendary = "False")))
    }
}