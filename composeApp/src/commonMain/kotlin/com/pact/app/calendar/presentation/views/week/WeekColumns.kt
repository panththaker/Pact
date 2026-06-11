package com.pact.app.calendar.presentation.views.week

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import com.pact.app.Primary
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

@Composable

fun WeekColumns(
    startOfWeek: LocalDate,
    todayDate: LocalDate
){
    val weekDays = (0..6).map { startOfWeek.plus(it, DateTimeUnit.DAY) }

    Row(modifier = Modifier
        .fillMaxWidth()
    ){
        weekDays.forEach { day ->
            val lineColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.9f)
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

            Box(modifier = Modifier.weight(1f)) {
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((HOUR_HEIGHT_DP * 24).dp)
                ) {
                    // today background
                    if (day == todayDate) {
                        drawRect(color = Primary.copy(alpha = 0.05f))
                    }

                    // vertical divider on left edge
                    drawLine(
                        color = lineColor,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height)
                    )

                    // horizontal dashed lines
                    repeat(23) { index ->
                        val y = (index + 1) * HOUR_HEIGHT_DP.dp.toPx()
                        drawLine(
                            color = lineColor,
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            pathEffect = pathEffect
                        )
                    }

                }
            }
        }
    }
}