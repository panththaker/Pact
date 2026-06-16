package com.pact.app.calendar.presentation.views.day

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pact.app.Primary
import com.pact.app.SurfaceWash
import com.pact.app.Text3
import com.pact.app.calendar.presentation.CalendarAction
import com.pact.app.calendar.presentation.CalendarState
import com.pact.app.core.utils.formatLong
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus

@Composable
fun DayView(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit
) {

    Column(){
        DayViewHeader(state, onAction)
        Spacer(modifier = Modifier.height(10.dp))
        DayViewSelector(state, onAction)
        Spacer(modifier = Modifier.height(10.dp))
        DayGrid(state, onAction)
    }

}

@Composable
private fun DayViewHeader(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = state.selectedDate.formatLong(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(SurfaceWash)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable(){ onAction(CalendarAction.OnDaySelected(state.todayDate.day)) }
        ) {
            Text(
                text = "Today",
                color = Primary,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Composable
private fun DayViewSelector(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit
){
    val stripDays = (-3..3).map { state.selectedDate.plus(it, DateTimeUnit.DAY) }
    Row(modifier = Modifier.fillMaxWidth()) {
        stripDays.forEach { day ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        color = when {
                            day == state.selectedDate -> Primary
                            else -> Color.Transparent
                        }
                    )
                    .clickable {onAction(CalendarAction.OnDaySelected(day.day))}
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = day.dayOfWeek.name.take(3).uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = if (day == state.selectedDate) Color.White else Text3
                )
                Text(
                    text = day.day.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (day == state.selectedDate) Color.White else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}