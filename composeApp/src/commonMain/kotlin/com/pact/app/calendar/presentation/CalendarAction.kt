package com.pact.app.calendar.presentation

sealed interface CalendarAction {
    data class OnSelectedViewChange(val view: CalendarViewType): CalendarAction
    data class OnDaySelected(val day: Int): CalendarAction

    // Month Actions
    data object OnPreviousMonth: CalendarAction
    data object OnNextMonth: CalendarAction

    // Week Actions

    // Day Actions
}