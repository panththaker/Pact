package com.pact.app.calendar.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

class CalendarViewModel: ViewModel(){

    private val _state = MutableStateFlow(CalendarState())
    val state: StateFlow<CalendarState> = _state.asStateFlow()

    fun onAction(action: CalendarAction){
        when(action){
            is CalendarAction.OnSelectedViewChange -> {
                _state.update { it.copy(selectedView = action.view) }
            }

            is CalendarAction.OnPreviousMonth -> {
                _state.update {it.copy(
                    selectedDate = it.selectedDate.minus(1, DateTimeUnit.MONTH)
                    )}
            }

            is CalendarAction.OnNextMonth -> {
                _state.update {it.copy(
                    selectedDate = it.selectedDate.plus(1, DateTimeUnit.MONTH)
                )}
            }

            is CalendarAction.OnDaySelected -> {
                _state.update{it.copy(
                    selectedDate = LocalDate(
                        it.selectedDate.year,
                        it.selectedDate.month,
                        action.day
                    )
                )}
            }
        }
    }

}