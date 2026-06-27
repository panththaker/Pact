package com.pact.app.calendar.presentation

import kotlinx.datetime.DateTimeUnit

sealed interface CalendarAction {
    data class OnSelectedViewChange(val view: CalendarViewType): CalendarAction
    data class OnDaySelected(val day: Int): CalendarAction

    data class OnPreviousUnitOfTime(val unitOfTime: DateTimeUnit.DateBased) : CalendarAction
    data class OnNextUnitOfTime(val unitOfTime: DateTimeUnit.DateBased) : CalendarAction

    data object LoadEvents: CalendarAction

}