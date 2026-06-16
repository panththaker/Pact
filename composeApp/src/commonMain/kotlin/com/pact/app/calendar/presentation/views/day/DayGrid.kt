package com.pact.app.calendar.presentation.views.day

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.pact.app.Primary
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pact.app.calendar.presentation.CalendarState
import com.pact.app.calendar.presentation.views.shared.TimeLabels
import com.pact.app.calendar.presentation.views.week.HOUR_HEIGHT_DP
import kotlinx.datetime.LocalDate
import androidx.compose.ui.graphics.PathEffect
import com.pact.app.calendar.presentation.CalendarAction

@Composable
fun DayGrid(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit,
){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Row(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        )
        {
            TimeLabels()
            Box(){
                DayGridBackground(
                    state.selectedDate,
                    state.todayDate
                )
                DayEvents(state.selectedDate, state.events)

            }
        }
    }
}

@Composable
private fun DayGridBackground(
    selectedDate: LocalDate,
    todayDate: LocalDate
){
    val lineColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.9f)

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height((HOUR_HEIGHT_DP * 24).dp)
    ) {


        // horizontal dashed lines
        repeat(23) { index ->
            val y = (index + 1) * HOUR_HEIGHT_DP.dp.toPx()
            drawLine(
                color = lineColor,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1f
            )
        }
    }
}