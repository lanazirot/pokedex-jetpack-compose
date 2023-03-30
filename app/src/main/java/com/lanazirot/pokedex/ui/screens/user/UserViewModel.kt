package com.lanazirot.pokedex.ui.screens.user


import androidx.lifecycle.ViewModel
import com.lanazirot.pokedex.domain.interfaces.IPokemonLocalRepository
import com.lanazirot.pokedex.domain.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val pokemonLocalRepository: IPokemonLocalRepository
) :ViewModel() {
    private val totalPokemon = 151

    private var _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    init {
        _userState.value = UserState(user = User(foundPokemonList = listOf(Pokemon(id = 1, name = "Bulbasaur", type1 = "Grass", type2 = "Poison", total = 318, hp = 45, attack = 49, defense = 49, spAtk = 65, spDef = 65, speed = 45, generation = 1, legendary = "False"), Pokemon( id = 3, name = "Venusaur", type1 = "Grass", type2 = "Poison", total = 525, hp = 80, attack = 82, defense = 83, spAtk = 100, spDef = 100, speed = 80, generation = 1, legendary = "False")), scoreLog = listOf()))
    }

    private fun updateUser(updatedUser : User) {
        _userState.value = userState.value.copy(user = updatedUser)
    }

    /**
     * Agrega un pokemon a la lista de pokemon encontrados por el usuario
     * @param pokemon pokemon encontrado
     */
    fun updatePokemonFound(pokemon: Pokemon) {
        val updatedUser = userState.value.user.copy(foundPokemonList = userState.value.user.foundPokemonList + pokemon)

        //Si los pokemones encontrados coinciden con el total de pokemones, entonces el pokedex esta completado
        if(updatedUser.foundPokemonList.count() == totalPokemon) {
            updatedUser.pokedexCompleted = true
        }

        updateUser(updatedUser)
    }

    /**
     * Agrega un score a la lista de scores del usuario. Asigna la fecha actual a la puntuacion
     * @param score score obtenido
     */
    fun updateScoreLog(score: Int) {
        val score :Score = Score(score = score, date = System.currentTimeMillis().toString())
        val updatedUser = userState.value.user.copy(scoreLog = userState.value.user.scoreLog + score)
        updateUser(updatedUser)
    }

    /**
     * Devuelve los tres socres mas altos obtenidos por el usuario
     * @return lista de scores
     */
    fun getTopThreeScores(): List<Score> {
        return _userState.value.user.scoreLog.sortedByDescending { it.score }.take(3)
    }

    /**
     * Devuelve toda la lista de pokemon encontrados por el usuario
     * @return lista de pokemon encontrados
     */
    fun getPokemonFound(): List<Pokemon> {
        return _userState.value.user.foundPokemonList
    }

    /**
     * Devuelve la cantidad de pokemon encontrados por el usuario
     * @return cantidad de pokemon encontrados
     */
    fun getCountPokemonFound(): Int {
        return _userState.value.user.foundPokemonList.count()
    }

    /**
     * Devuelve la cantidad de pokemon encontrados por el usuario de un tipo especifico
     * @param type tipo de pokemon
     * @return cantidad de pokemon encontrados de ese tipo en especifico
     */
    fun getCountPokemonFoundByType(type: String): Int {
        return _userState.value.user.foundPokemonList.map {
            pokemon: Pokemon ->
                pokemon.type1 == type || pokemon.type2 == type }.count {
            it
        }
    }

    /**
     * Devuelve la cantidad de pokemon legendarios encontrados por el usuario
     * @return cantidad de pokemon legendarios encontrados
     */
    fun getCountPokemonLegendaryFound(): Int {
        return _userState.value.user.foundPokemonList.map {
            pokemon: Pokemon ->
                pokemon.legendary == "True" }.count {
            it
        }
    }

    /**
     * Devuelve la cantidad de intentos realizados por el usuario
     * @return cantidad de intentos realizados
     */
    fun getAttemptsNumber(): Int {
        return _userState.value.user.scoreLog.count()
    }

    /**
     * Genera un porcentaje de progreso de la pokedex en base a la cantidad de pokemon encontrados
     * @return porcentaje de progreso del juego
     */
    fun getPokedexProgress(): Int {
        val countPokemonFound = getCountPokemonFound()
        return if (countPokemonFound > 0) (countPokemonFound * 100) / totalPokemon else 0
    }

    suspend fun getNotFoundPokemon(): MutableList<Pokemon> {
        val foundPokemonList = getPokemonFound()
        val notFoundPokemonList = mutableListOf<Pokemon>()
        pokemonLocalRepository.getPokemonList().forEach {
            if(!foundPokemonList.contains(it)) {
                notFoundPokemonList.add(it)
            }
        }
        return notFoundPokemonList
    }

    suspend fun getAllPokemons(): List<Pokemon> {
        return pokemonLocalRepository.getPokemonList()
    }
}

