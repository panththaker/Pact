package com.pact.app.calendar.presentation.views.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pact.app.calendar.presentation.views.week.HOUR_HEIGHT_DP

@Composable
fun TimeLabels(){
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