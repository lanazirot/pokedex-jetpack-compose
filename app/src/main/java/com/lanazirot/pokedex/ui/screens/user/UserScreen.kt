package com.lanazirot.pokedex.ui.screens.user

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lanazirot.pokedex.ui.providers.GlobalUserProvider

@Composable
fun UserScreen() {
    val currentUser = GlobalUserProvider.current.currentUser
    val scrollState = rememberScrollState()

    BoxWithConstraints {
//        Column(
//            modifier = Modifier
//                .verticalScroll(scrollState)
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {

            LazyColumn(
//                modifier = Modifier.weight(),
                content =  {
                    item {
                        Text(text="Mis estadisticas", Modifier.size(size = 22.dp))
                    }
                    items(currentUser.getTopThreeScores()) { p ->
                        Text(text = p.score.toString())
                    }
                    item {
                        Text(text = "pokedex completada" + currentUser.isPokedexCompleted())

                        Text(text = "count enciontrados" + currentUser.pokemonFoundCount())

                        Text(text = "count no encontrados" + currentUser.pokemonNotFoundCount())

                        Text(text = "tipo porr.........")

                        Text(text = "count legendarios" + currentUser.pokemonLegendaryFoundCount())

                        Text(text = "numero de intentos" + currentUser.attemptsCount())
                    }
//                        items(currentUser.getPokemonFound()) {
//                                p -> PokemonSimpleCard(pokemon = p, isVisible = true)
//                        }

                }
            )

//            if(currentUser.isPokedexCompleted())
//                Image()
//        }
    }
}
