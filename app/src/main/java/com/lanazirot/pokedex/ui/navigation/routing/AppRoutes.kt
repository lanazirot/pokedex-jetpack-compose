package com.lanazirot.pokedex.ui.navigation.routing

class AppRoutes {

    object Pokedex {
        const val PokemonList = "pokemon_list"
    }

    object Leaderboard {
        const val Leaderboard = "leaderboard"
    }

    object Play {
        const val Game = "game"
        const val GameResult = "game_result/{gameProgressResult}"
        fun gameResult(gameProgressResult: String) = "game_result/$gameProgressResult"
    }

    object User {
        const val Profile = "profile"
    }

    object Login {
        const val Login = "login"
    }

}