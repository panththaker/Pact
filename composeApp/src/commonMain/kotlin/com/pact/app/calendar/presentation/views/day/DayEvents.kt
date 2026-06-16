package com.pact.app.calendar.presentation.views.day

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pact.app.calendar.presentation.views.shared.GridEvent
import com.pact.app.calendar.presentation.views.week.HOUR_HEIGHT_DP
import com.pact.app.core.domain.PactEvent
import kotlinx.datetime.LocalDate

@Composable
fun DayEvents(
    selectedDate: LocalDate,
    events: List<PactEvent>
) {
    val dayEvents = events.filter { it.date == selectedDate }

    Box(modifier = Modifier.fillMaxWidth()) {
        dayEvents.forEach { event ->
            val yOffset = (event.startTime / 60f * HOUR_HEIGHT_DP).dp
            val height = ((event.endTime - event.startTime) / 60f * HOUR_HEIGHT_DP).dp

            GridEvent(
                event = event,
                modifier = Modifier
                    .offset(y = yOffset)
                    .fillMaxWidth()
                    .height(height)
            )
        }
    }
}