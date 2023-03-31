package com.lanazirot.pokedex.ui.screens.game

import com.lanazirot.pokedex.domain.models.Pokemon

sealed class GameUI {
    object Loading : GameUI()
    object Playing : GameUI()
    class  PokemonFetched(var pokemon: Pokemon): GameUI()
    object FetchingPokemon : GameUI()
}