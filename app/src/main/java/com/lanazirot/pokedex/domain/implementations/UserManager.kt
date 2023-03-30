package com.lanazirot.pokedex.domain.implementations

import com.lanazirot.pokedex.domain.interfaces.IUserManager
import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.domain.models.User
import java.util.*
import javax.inject.Inject

class UserManager @Inject constructor() : IUserManager {
    private var currentUser: User? = null

    init {
        currentUser = User()
    }

    override fun setCurrentUser(user: User) {
        currentUser = user
    }

    override fun getCurrentUser(): User? {
        return currentUser
    }

    override fun addSeenPokemon(pokemon: Pokemon) {
        currentUser?.foundPokemonList?.plus(pokemon)
    }

    override fun addToScoreLog(score: Int) {

    }
}