package com.pact.app.calendar.presentation.event.sheets

import com.pact.app.core.domain.ReminderType
import com.pact.app.core.domain.RepeatType
import kotlinx.datetime.LocalDate

sealed interface BottomSheetType {
    data object DateSheet : BottomSheetType
    data object TimeSheetStartTime : BottomSheetType
    data object TimeSheetEndTime : BottomSheetType
    data object ReminderSheet: BottomSheetType
    data object RepeatSheet: BottomSheetType
}