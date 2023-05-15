package com.lanazirot.pokedex.converters

fun elapsedTime(time: Long): String {
    val seconds = (time / 1000) % 60
    val minutes = (time / (1000 * 60)) % 60
    val hours = (time / (1000 * 60 * 60)) % 24
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}