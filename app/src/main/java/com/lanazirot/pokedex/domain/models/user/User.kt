package com.lanazirot.pokedex.domain.models.user

data class User(
    val name: String = "",
    val email: String = "",
    val photoUrl: String = "",
    var country: String = "",
    val currentUserData: UserData = UserData(),
)