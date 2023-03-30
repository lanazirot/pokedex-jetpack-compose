package com.lanazirot.pokedex.domain.interfaces

interface IPokemonGuessService {
    suspend fun guessPokemon(id: Int): Boolean
}