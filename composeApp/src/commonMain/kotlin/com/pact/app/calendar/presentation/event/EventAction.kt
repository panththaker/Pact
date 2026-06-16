package com.pact.app.calendar.presentation.event

import com.pact.app.core.domain.PactEvent

sealed interface EventAction {
    data object OnSaveEventFormScreen: EventAction
    data class PopulateForm(val event: PactEvent): EventAction


    data class OnTaskTitleChange(val taskName: String): EventAction

}