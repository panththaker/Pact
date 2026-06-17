package com.pact.app.calendar.presentation.event.sheets

import com.pact.app.core.domain.ReminderType
import com.pact.app.core.domain.RepeatType
import kotlinx.datetime.LocalDate

sealed interface BottomSheetType {
    data class DateSheet(val date: LocalDate) : BottomSheetType
    data object TimeSheetStartTime : BottomSheetType
    data object TimeSheetEndTime : BottomSheetType
    data object ReminderSheet: BottomSheetType
    data class RepeatSheet(val repeat: RepeatType): BottomSheetType
}