package com.pact.app.calendar.presentation.views.month

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pact.app.SurfaceWash
import com.pact.app.Text1
import com.pact.app.Text3
import com.pact.app.calendar.presentation.CalendarAction
import com.pact.app.calendar.presentation.CalendarState
import com.pact.app.core.ui.LinkText
import com.pact.app.core.utils.formatShort
import com.pact.app.core.utils.toTimeString


@Composable
fun MonthEventList(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit
) {
    MonthEventListHeader(state, onAction)
}

@Composable
private fun MonthEventListHeader(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit
){
    val selectedDate = state.selectedDate
    val numberOfEventsOnDate = state.events.count { it.date == state.selectedDate }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(){
            Text(
                text = if (selectedDate == state.todayDate) "Today" else selectedDate.formatShort(),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "·",
                style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "$numberOfEventsOnDate events",
                style = MaterialTheme.typography.titleMedium
            )
        }
        LinkText("See All", {}) // TODO: Add an onaction to this
    }
    Spacer(modifier = Modifier.height(12.dp))
    MonthEventListRows(state, onAction)
}

@Composable
private fun MonthEventListRows(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit
) {
    val selectedDateEvents = state.events.filter { it.date == state.selectedDate }
    for ((index, event) in selectedDateEvents.withIndex()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = event.startTime.toTimeString(),
                style = MaterialTheme.typography.titleMedium,
                color = Text3,
                fontSize = 14.sp,
                modifier = Modifier.width(52.dp),
                maxLines = 1,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(20.dp)
                    .background(
                        color = Color(event.color),
                        shape = RoundedCornerShape(2.dp)
                    ),

                )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = event.title,
                style = MaterialTheme.typography.bodyMedium,
                color = Text1
            )
        }
        if (index < selectedDateEvents.size - 1) {
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = SurfaceWash)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}