package com.lanazirot.pokedex.ui.screens.user

import com.lanazirot.pokedex.domain.models.user.User

data class UserState(
    var user: User = User()
)