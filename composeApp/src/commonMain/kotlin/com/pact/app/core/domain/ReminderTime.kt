package com.pact.app.core.domain
enum class ReminderType(val label: String) {
    NONE("None"),
    AT_TIME("At time"),
    FIVE_MIN("5 min before"),
    TEN_MIN("10 min before"),
    THIRTY_MIN("30 min before"),
    ONE_HOUR("1 hour before"),
    CUSTOM("Custom")
}

data class ReminderTime(
    val type: ReminderType,
    val customMinutes: Int? = null // Only set this if reminder type is custom
)