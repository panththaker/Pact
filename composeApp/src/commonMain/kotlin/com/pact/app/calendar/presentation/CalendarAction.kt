package com.pact.app.calendar.presentation

sealed interface CalendarAction {
    data class OnSelectedViewChange(val view: CalendarView): CalendarAction
}