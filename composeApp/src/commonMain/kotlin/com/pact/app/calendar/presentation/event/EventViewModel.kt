package com.pact.app.calendar.presentation.event

import androidx.lifecycle.ViewModel
import jdk.jfr.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EventViewModel(
): ViewModel(){
    private val _state = MutableStateFlow(EventState())
    val state: StateFlow<EventState> = _state.asStateFlow()

    fun OnAction(action: EventAction){
        when(action){
            is EventAction.OnSaveEventFormScreen -> {
                // Implement here
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
        }
    }
}