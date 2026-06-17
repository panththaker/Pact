package com.pact.app.core.domain

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class PactEvent(
    val id: String,           // unique identifier
    val title: String,        // "Deep focus: calendar mocks"
    val date: LocalDate,      // which day it falls on
    val startTime: Int, // "9:30 AM"
    val endTime: Int,   // "11:00 AM"
    val color: Long,         // the color tag
    val reminder: ReminderTime,       // minutes before, null = no reminder
    val repeat: RepeatType,   // enum: NONE, DAILY, WEEKLY, etc.
    val notes: String?,       // optional notes field
    val isCompleted: Boolean  // checkable in to do view
)