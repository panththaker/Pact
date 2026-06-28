package com.pact.app.core.utils

import kotlinx.datetime.LocalDateTime

fun TimeOfDayGreeting(currentDateTime: LocalDateTime): String {
    val hour = currentDateTime.hour

    return when {
        hour < 12 -> "Good morning"
        hour < 17 -> "Good afternoon"
        else -> "Good evening"
    }
}