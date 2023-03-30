package com.lanazirot.pokedex.ui.screens.bottomappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes

sealed class BottomNavItem(var title:String, var icon:ImageVector, var screen_route:String){
    object User : BottomNavItem("Home", Icons.Filled.Person,AppRoutes.User.Profile)
    object Pokedex : BottomNavItem("Pokedex", Icons.Filled.Person,AppRoutes.Pokedex.PokemonList)
}