package com.pact.app.calendar.presentation.event

import com.pact.app.core.domain.RepeatType
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock


// Event State stores default values until populated
data class EventState(
    val id: String? = null,
    val title: String = "",
    val date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    val startTime: Int = 540,  // 9:00 AM in minutes
    val endTime: Int = 600,    // 10:00 AM in minutes
    val color: Long = 0xFF9B8EC4,
    val reminder: Int? = null,
    val repeat: RepeatType = RepeatType.NONE,
    val notes: String? = null,
    val isCompleted: Boolean = false,

    // UI States
    val todayDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

){
    // This is a computed property
    val isAddMode: Boolean get() = id == null
}