package com.pact.app.calendar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pact.app.calendar.domain.CalendarRepository
import com.pact.app.core.domain.SessionManager
import com.pact.app.core.domain.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

class CalendarViewModel(
    private val sessionManager: SessionManager,
    private val calendarRepository: CalendarRepository
): ViewModel(){

    private val _state = MutableStateFlow(CalendarState())
    val state: StateFlow<CalendarState> = _state.asStateFlow()
    val session: StateFlow<UserSession?> = sessionManager.session

    fun onAction(action: CalendarAction){
        when(action){
            is CalendarAction.LoadEvents -> {
                viewModelScope.launch {
                    calendarRepository.getAllEvents()
                        .onSuccess { events -> _state.update { it.copy(events = events) }  }
                        .onFailure { _state.update { it.copy(errorMessage = "Failed to get events") } }
                }
            }
            is CalendarAction.OnSelectedViewChange -> {
                _state.update { it.copy(selectedView = action.view) }
            }

            is CalendarAction.OnPreviousUnitOfTime -> {
                _state.update {it.copy(
                    selectedDate = it.selectedDate.minus(1, action.unitOfTime)
                    )}
            }

            is CalendarAction.OnNextUnitOfTime -> {
                _state.update {it.copy(
                    selectedDate = it.selectedDate.plus(1, action.unitOfTime)
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