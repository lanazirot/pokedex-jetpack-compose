package com.lanazirot.pokedex.ui.screens.game.states

import com.lanazirot.pokedex.domain.models.game.Pokemon

sealed class GameUIState {
    object Loading : GameUIState()
    object Playing : GameUIState()
    class  PokemonFetched(var pokemon: Pokemon): GameUIState()
    object FetchingPokemon : GameUIState()
    object ShowingResult : GameUIState()
}