package com.pact.app.calendar.presentation.views.week

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pact.app.SurfaceWash
import com.pact.app.calendar.presentation.CalendarAction
import com.pact.app.calendar.presentation.CalendarState
import com.pact.app.icons.chevron_left
import com.pact.app.icons.chevron_right
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.plus

const val HOUR_HEIGHT_DP = 48
@Composable
fun WeekView(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit
) {
    var numberOfDaysToSubtract: Int = state.selectedDate.dayOfWeek.isoDayNumber
    if(numberOfDaysToSubtract == 7){
        numberOfDaysToSubtract = 0
    }
    val month = state.selectedDate.month.name.lowercase().replaceFirstChar { it.uppercase() }
    val startOfWeek: LocalDate = state.selectedDate.minus(numberOfDaysToSubtract, DateTimeUnit.DAY)
    val endOfWeek: LocalDate = startOfWeek.plus(6, DateTimeUnit.DAY)


    Column(){
        WeekViewHeader(state, onAction, month, startOfWeek, endOfWeek)
        Spacer(modifier = Modifier.height(10.dp))
        WeekGrid(state, onAction, startOfWeek)
    }
}

@Composable
private fun WeekViewHeader(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit,
    month: String,
    startOfWeek: LocalDate,
    endOfWeek: LocalDate
){

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ){
        Text(
            text = "${month} ${startOfWeek.day} - ${endOfWeek.day}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            // Left Arrow
            IconButton(
                modifier = Modifier
                    .background(color = SurfaceWash,
                        shape = RoundedCornerShape(10.dp)),
                onClick = {onAction(CalendarAction.OnPreviousUnitOfTime(DateTimeUnit.WEEK))},
                shape = RoundedCornerShape(10.dp)
            ){
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    imageVector = chevron_left,
                    contentDescription = "Previous week"
                )
            }

            // Right Arrow
            IconButton(
                modifier = Modifier
                    .background(color = SurfaceWash,
                        shape = RoundedCornerShape(10.dp)),
                onClick = {onAction(CalendarAction.OnNextUnitOfTime(DateTimeUnit.WEEK))},
                shape = RoundedCornerShape(10.dp)
            ){
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    imageVector = chevron_right,
                    contentDescription = "Next week"
                )
            }

        }
    }
}