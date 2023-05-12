package com.lanazirot.pokedex.ui.screens.user


import androidx.lifecycle.ViewModel
import com.lanazirot.pokedex.domain.constants.GameConstants
import com.lanazirot.pokedex.domain.interfaces.game.IUserManager
import com.lanazirot.pokedex.domain.models.game.Pokemon
import com.lanazirot.pokedex.domain.models.game.Score
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userManager: IUserManager) :ViewModel() {

    /**
     * Agrega un pokemon a la lista de pokemon encontrados por el usuario
     * @param pokemon pokemon encontrado
     */
    fun addSeenPokemon(pokemon: Pokemon) {
        userManager.addSeenPokemon(pokemon)
    }

    /**
     * Agrega un score a la lista de scores del usuario. Asigna la fecha actual a la puntuacion
     * @param score score obtenido
     */
    fun updateScoreLog(score: Int) {
        userManager.addToScoreLog(score)
    }

    /**
     * Devuelve los tres socres mas altos obtenidos por el usuario
     * @return lista de scores
     */
    fun getTopThreeScores(): List<Score> {
        return userManager.getTopThreeScores()
    }

    /**
     * Devuelve toda la lista de pokemon encontrados por el usuario
     * @return lista de pokemon encontrados
     */
    fun getPokemonFound(): List<Pokemon> {
        return userManager.getPokemonFound()
    }

    /**
     * Devuelve la cantidad de pokemon encontrados por el usuario
     * @return cantidad de pokemon encontrados
     */
    fun pokemonFoundCount(): Int {
        return getPokemonFound().count()
    }

    fun pokemonNotFoundCount(): Int {
        return GameConstants.TOTAL_POKEMON - pokemonFoundCount()
    }

    /**
     * Devuelve la cantidad de pokemon encontrados por el usuario de un tipo especifico
     * @param type tipo de pokemon
     * @return cantidad de pokemon encontrados de ese tipo en especifico
     */
    fun pokemonFoundByTypeCount(type: String): Int {
        return userManager.pokemonFoundByTypeCount(type)
    }

    /**
     * Devuelve la cantidad de pokemon legendarios encontrados por el usuario
     * @return cantidad de pokemon legendarios encontrados
     */
    fun pokemonLegendaryFoundCount(): Int {
        return userManager.pokemonLegendaryFoundCount()
    }

    /**
     * Devuelve la cantidad de intentos realizados por el usuario
     * @return cantidad de intentos realizados
     */
    fun attemptsCount(): Int {
        return userManager.attemptsCount()
    }

    /**
     * Genera un porcentaje de progreso de la pokedex en base a la cantidad de pokemon encontrados
     * @return porcentaje de progreso del juego
     */
    fun getPokedexProgress(): Int {
        return userManager.getPokedexProgress()
    }

    suspend fun getPokemonNotFound(): MutableList<Pokemon> {
        return userManager.getPokemonNotFound().toMutableList()
    }

    suspend fun getAllPokemonList(): List<Pokemon> {
        return userManager.getAllPokemonList()
    }

    fun isPokedexCompleted(): Boolean {
        return userManager.isPokedexCompleted()
    }
}

