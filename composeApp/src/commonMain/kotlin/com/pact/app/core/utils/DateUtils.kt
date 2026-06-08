package com.pact.app.core.utils

import kotlinx.datetime.LocalDate

fun LocalDate.formatDisplay(): String {
    val dow = dayOfWeek.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
    val mon = month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
    return "$dow, $mon $dayOfMonth"
}

fun LocalDate.formatShort(): String {
    val mon = month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
    return "$mon $dayOfMonth"
}

// Converts mins from midnight to 9:30 AM type thing
fun Int.toTimeString(): String {
    val hours = this / 60
    val minutes = this % 60
    val displayHour = if (hours % 12 == 0) 12 else hours % 12
    return "$displayHour:${minutes.toString().padStart(2, '0')}"
}