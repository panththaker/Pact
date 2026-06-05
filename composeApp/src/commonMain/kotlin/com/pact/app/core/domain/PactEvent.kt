package com.pact.app.core.domain

import java.awt.Color
import java.time.LocalDate
import java.time.LocalTime

data class PactEvent(
    val id: String,           // unique identifier
    val title: String,        // "Deep focus: calendar mocks"
    val date: LocalDate,      // which day it falls on
    val startTime: LocalTime, // "9:30 AM"
    val endTime: LocalTime,   // "11:00 AM"
    val color: Color,         // the color tag
    val reminder: Int?,       // minutes before, null = no reminder
    val repeat: RepeatType,   // enum: NONE, DAILY, WEEKLY, etc.
    val notes: String?,       // optional notes field
    val isCompleted: Boolean  // checkable in to do view
)