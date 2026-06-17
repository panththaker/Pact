package com.pact.app.calendar.presentation.event

import com.pact.app.calendar.presentation.event.sheets.BottomSheetType
import com.pact.app.core.domain.PactEvent
import com.pact.app.core.domain.ReminderTime

sealed interface EventAction {
    data object OnSaveEventFormScreen: EventAction
    data class PopulateForm(val event: PactEvent): EventAction


    data class OnTaskTitleChange(val taskName: String): EventAction
    data class OnNotesChange(val notes: String): EventAction
    data class OnColorChange(val color: Long): EventAction

    data class OpenSheet(val type: BottomSheetType): EventAction
    data object CloseSheet: EventAction
    data class ConfirmTime(val minutes: Int, val isStartTime: Boolean) : EventAction
    data class ConfirmReminder(val reminder: ReminderTime) : EventAction
//    data class ConfirmDate(val date: LocalDate) : EventAction
//    data class ConfirmRepeat(val repeat: RepeatType) : EventAction



}