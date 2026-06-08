package com.pact.app.calendar.presentation.views.month

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pact.app.Surface
import com.pact.app.calendar.presentation.CalendarAction
import com.pact.app.calendar.presentation.CalendarState
import com.pact.app.icons.chevron_left
import com.pact.app.icons.chevron_right
import com.pact.app.icons.keyboard_arrow_down
import androidx.compose.ui.Alignment


@Composable
fun MonthView(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Surface, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        MonthHeader(state, onAction)
        Spacer(Modifier.height(8.dp))
        MonthGrid(state, onAction)
    }
    Spacer(Modifier.height(16.dp))
    MonthEventList(state, onAction)

}

@Composable
private fun MonthHeader(
    state: CalendarState,
    onAction: (CalendarAction) -> Unit
){

    val monthAbbrev: String= state.selectedDate.month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
    val year: Int = state.selectedDate.year

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        // Date and Chevron Down
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ){

            Row(){
                Text(
                    text = monthAbbrev,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "$year",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            IconButton(
                onClick = {}
            ){
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    imageVector = keyboard_arrow_down,
                    contentDescription = "Select month"
                )
            }

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            // Left Arrow
            IconButton(
                onClick = {onAction(CalendarAction.OnPreviousMonth)}
            ){
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    imageVector = chevron_left,
                    contentDescription = "Previous month"
                )
            }

            // Right Arrow
            IconButton(
                onClick = {onAction(CalendarAction.OnNextMonth)}
            ){
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    imageVector = chevron_right,
                    contentDescription = "Next month"
                )
            }

        }

    }

}