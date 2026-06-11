package com.pact.app.calendar.presentation.views.week

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pact.app.core.domain.PactEvent
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus


@Composable
fun WeekEvents(
    startOfWeek: LocalDate,
    events: List<PactEvent>
) {
    val weekDays = (0..6).map { startOfWeek.plus(it, DateTimeUnit.DAY) }
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val columnWidth = maxWidth / 7
        weekDays.forEachIndexed { columnIndex, day ->
            val dayEvents = events.filter { it.date == day }
            dayEvents.forEach { event ->
                val xOffset = columnWidth * columnIndex
                val yOffset = (event.startTime / 60f * HOUR_HEIGHT_DP).dp
                val height = ((event.endTime - event.startTime) / 60f * HOUR_HEIGHT_DP).dp

                WeekEvent(
                    event = event,
                    modifier = Modifier
                        .offset(x = xOffset, y = yOffset)
                        .width(columnWidth)
                        .height(height)
                )
            }
        }
    }
}

@Composable
private fun WeekEvent(
    event: PactEvent,
    modifier: Modifier
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(event.color))
            .padding(4.dp)
    ) {
        Text(
            text = event.title,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}