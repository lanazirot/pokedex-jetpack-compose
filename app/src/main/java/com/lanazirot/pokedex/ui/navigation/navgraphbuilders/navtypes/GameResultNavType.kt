package com.lanazirot.pokedex.ui.navigation.navgraphbuilders.navtypes

import com.google.gson.Gson
import com.lanazirot.pokedex.domain.models.GameProgressResult
import com.lanazirot.pokedex.ui.navigation.JsonNavType

class ProfileArgType : JsonNavType<GameProgressResult>() {
    override fun fromJsonParse(value: String): GameProgressResult = Gson().fromJson(value, GameProgressResult::class.java)
    override fun GameProgressResult.getJsonParse(): String = Gson().toJson(this)
}