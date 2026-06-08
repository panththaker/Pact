package com.pact.app.calendar.presentation.views.month

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pact.app.calendar.presentation.CalendarAction
import com.pact.app.calendar.presentation.CalendarState
import kotlinx.datetime.LocalDate
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.Month


@Composable
fun MonthGrid(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit
) {

    val isLeapYear: Boolean = state.selectedDate.year % 4 == 0
    val daysInSelectedMonth = state.selectedDate.month.length(isLeapYear)


    val firstDayOfSelectedMonth = LocalDate(state.selectedDate.year, state.selectedDate.month, 1)
    val numberOfNulls = firstDayOfSelectedMonth.dayOfWeek.isoDayNumber % 7


    val leadingNulls = List(numberOfNulls) { null }
    val dayNumbers = List(daysInSelectedMonth) { it + 1 }
    val trailingNulls = List(42 - numberOfNulls - daysInSelectedMonth) { null }

    val days: List<Int?> = leadingNulls + dayNumbers + trailingNulls
    val weeks = days.chunked(7)

    val daysOfWeek: Array<String> = arrayOf("S", "M", "T", "W", "T", "F", "S");


    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            for (dayOfWeek in daysOfWeek) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(dayOfWeek)
                }
            }
        }

        // Days
        for (week in weeks) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                for (day in week) {

                    val isToday = day != null &&
                            day == state.todayDate.dayOfMonth &&
                            state.selectedDate.month == state.todayDate.month &&
                            state.selectedDate.year == state.todayDate.year

                    val isSelected = day != null && day == state.selectedDate.dayOfMonth

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.weight(1f)
                    ) {
                        MonthDayCell(
                            day = day,
                            isToday = isToday,
                            isSelected = isSelected,
                            onClick = {if (day != null) onAction(CalendarAction.OnDaySelected(day))},
                            hasEvents = day != null && state.events.any {
                                it.date == LocalDate(state.selectedDate.year, state.selectedDate.month, day)
                            }
                        )
                    }
                }
            }
        }


    }

}