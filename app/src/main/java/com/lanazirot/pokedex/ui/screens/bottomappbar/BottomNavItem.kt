package com.lanazirot.pokedex.ui.screens.bottomappbar

import androidx.annotation.DrawableRes
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes

sealed class BottomNavItem(var title:String,@DrawableRes var icon: Int, var screen_route:String){
    object User : BottomNavItem("Home",  R.drawable.profile  ,AppRoutes.User.Profile)
    object Pokedex : BottomNavItem("Pokedex", R.drawable.pokedex ,AppRoutes.Pokedex.PokemonList)
}