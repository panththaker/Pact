package com.pact.app.calendar.presentation.views.week

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pact.app.Primary
import com.pact.app.calendar.presentation.CalendarAction
import com.pact.app.calendar.presentation.CalendarState
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun WeekGrid(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit,
    startOfWeek: LocalDate,
){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        WeekDayHeaders(startOfWeek, state.todayDate)
        Row(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
            )
        {
            TimeLabels()
            Box(){
                WeekColumns(startOfWeek, state.todayDate)
                WeekEvents(startOfWeek, state.events)
            }
        }
    }

}

@Composable
private fun WeekDayHeaders(
    startOfWeek: LocalDate,
    todayDate: LocalDate
){
    val weekDays = (0..6).map { startOfWeek.plus(it, DateTimeUnit.DAY) }

    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(30.dp))
        weekDays.forEach { weekday ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val abbrevWeekDay = weekday.dayOfWeek.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
                Text(abbrevWeekDay)

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            color = if (weekday == todayDate) Primary else Color.Transparent,
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = weekday.day.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (weekday == todayDate) Color.White else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
private fun TimeLabels(){
    val times: List<Int> = (1..23).toList()
    Column {
        for(time in times){
            val label = when {
                time == 12 -> "12 PM"
                time > 12 -> "${time - 12} PM"
                else -> "$time AM"
            }
            Box(
                modifier = Modifier
                    .height(HOUR_HEIGHT_DP.dp)
                    .wrapContentWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

}