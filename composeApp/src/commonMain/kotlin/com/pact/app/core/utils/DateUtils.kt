package com.pact.app.core.utils

import kotlinx.datetime.LocalDate


// Formats to "Tuesday, May 12"
fun LocalDate.formatLong(): String {
    val dow = dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
    val mon = month.name.lowercase().replaceFirstChar { it.uppercase() }
    return "$dow, $mon $day"
}

// Formats to "Tue, Jun 14"
fun LocalDate.formatDisplay(): String {
    val dow = dayOfWeek.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
    val mon = month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
    return "$dow, $mon $day"
}

// Formats to "Jun 14"
fun LocalDate.formatShort(): String {
    val mon = month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
    return "$mon $day"
}
