package com.lanazirot.pokedex.converters

fun elapsedTime(seconds: Long): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val seconds = seconds % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

fun Long.toFormattedTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val seconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}