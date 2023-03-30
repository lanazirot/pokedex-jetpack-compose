package com.lanazirot.pokedex.converters

import com.google.gson.Gson

fun <T> String.jsonMap(clazz: Class<T>): T {
    return Gson().fromJson(this, clazz)
}
