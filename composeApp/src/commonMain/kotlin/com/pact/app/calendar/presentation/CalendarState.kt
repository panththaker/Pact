package com.pact.app.calendar.presentation

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

data class CalendarState(
    val selectedView: CalendarView = CalendarView.MONTH,
    val selectedDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
)