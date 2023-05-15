package com.lanazirot.pokedex.converters

fun elapsedTime(seconds: Long): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val seconds = seconds % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}