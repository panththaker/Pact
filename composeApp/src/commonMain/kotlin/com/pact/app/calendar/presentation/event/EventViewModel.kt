package com.pact.app.calendar.presentation.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pact.app.calendar.domain.CalendarRepository
import com.pact.app.core.domain.PactEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class EventViewModel(
    private val calendarRepository: CalendarRepository
): ViewModel(){
    private val _state = MutableStateFlow(EventState())
    val state: StateFlow<EventState> = _state.asStateFlow()

    @OptIn(ExperimentalUuidApi::class)
    fun onAction(action: EventAction){
        when(action){
            is EventAction.OnSaveEventFormScreen -> {
                viewModelScope.launch {
                    val currentState = _state.value
                    val event = PactEvent(
                        id = currentState.id ?: Uuid.random().toString(),
                        title = currentState.title,
                        date = currentState.date,
                        startTime = currentState.startTime,
                        endTime = currentState.endTime,
                        color = currentState.color,
                        reminder = currentState.reminder,
                        repeat = currentState.repeat,
                        notes = currentState.notes,
                        isCompleted = currentState.isCompleted
                    )

                    val result = if (currentState.isAddMode) {
                        calendarRepository.createEvent(event)
                    } else {
                        calendarRepository.updateEvent(event)
                    }

                    result
                        .onSuccess { /* signal success — navigate back? */ }
                        .onFailure { /* set an error state */ }
                }
            }

            is EventAction.PopulateForm -> {
                _state.update {
                    it.copy(
                        id = action.event.id,
                        title = action.event.title,
                        date = action.event.date,
                        startTime = action.event.startTime,
                        endTime = action.event.endTime,
                        color = action.event.color,
                        reminder = action.event.reminder,
                        repeat = action.event.repeat,
                        notes = action.event.notes,
                        isCompleted = action.event.isCompleted
                    )
                }
            }

            is EventAction.OnTaskTitleChange -> {
                _state.update {
                    it.copy(
                        title = action.taskName
                    )
                }
            }

            is EventAction.OnNotesChange -> {
                _state.update {
                    it.copy(
                        notes = action.notes
                    )
                }
            }
            is EventAction.OnColorChange -> {
                _state.update {
                    it.copy(
                        color = action.color
                    )
                }
            }

            is EventAction.OnDurationSelected ->
                _state.update {
                    it.copy(
                        endTime = it.startTime + action.duration
                    )
                }


            is EventAction.OpenSheet -> {
                _state.update {
                    it.copy(
                        activeSheet = action.type
                    )
                }
            }

            is EventAction.CloseSheet -> {
                _state.update {
                    it.copy(
                        activeSheet = null
                    )
                }
            }

            is EventAction.ConfirmTime -> {
                _state.update {
                    it.copy(
                        startTime = if (action.isStartTime) action.minutes else it.startTime,
                        endTime = if (!action.isStartTime) action.minutes else it.endTime,
                        activeSheet = null
                    )
                }
            }

            is EventAction.ConfirmReminder -> {
                _state.update {
                    it.copy(
                        reminder = action.reminder,
                        activeSheet = null
                    )
                }
            }

            is EventAction.ConfirmRepeat -> {
                _state.update {
                    it.copy(
                        repeat = action.repeatType,
                        activeSheet = null
                    )
                }
            }

            is EventAction.ConfirmDate -> {
                _state.update {
                    it.copy(
                        date = action.date,
                        activeSheet = null
                    )
                }
            }
        }
    }
}