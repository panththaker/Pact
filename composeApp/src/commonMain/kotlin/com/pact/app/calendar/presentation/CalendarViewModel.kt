package com.pact.app.calendar.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalendarViewModel: ViewModel(){

    private val _state = MutableStateFlow(CalendarState())
    val state: StateFlow<CalendarState> = _state.asStateFlow()

    fun onAction(action: CalendarAction){
        when(action){
            is CalendarAction.OnSelectedViewChange -> {
                _state.update { it.copy(selectedView = action.view) }
            }
        }
    }

}