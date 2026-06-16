package com.pact.app.core.utils

private const val AFTERNOON_THRESHOLD = 720  // 12:00 PM
private const val EVENING_THRESHOLD = 1020   // 5:00 PM

// Converts mins from midnight to 9:30 AM type thing
// Formats to "9:30" (540 → "9:30", 780 → "1:00", 1035 → "5:15")
fun Int.toTimeString(): String {
    val hours = this / 60
    val minutes = this % 60
    val displayHour = if (hours % 12 == 0) 12 else hours % 12
    return "$displayHour:${minutes.toString().padStart(2, '0')}"
}

fun Int.toDayPeriod(): String {
    return when {
        this < AFTERNOON_THRESHOLD -> "Morning"
        this < EVENING_THRESHOLD -> "Afternoon"
        else -> "Evening"
    }
}

fun Int.toDuration(): String {
    val totalMinutes = this
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return when {
        hours == 0 -> "${minutes}m"
        minutes == 0 -> "${hours}h"
        else -> "${hours}h ${minutes}m"
    }
}