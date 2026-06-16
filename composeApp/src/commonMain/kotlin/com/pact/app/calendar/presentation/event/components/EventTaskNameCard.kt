package com.pact.app.calendar.presentation.event.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pact.app.Text3
import com.pact.app.calendar.presentation.event.EventAction
import com.pact.app.core.utils.toDayPeriod
import com.pact.app.core.utils.toDuration

@Composable
fun EventTaskNameCard(
    onAction: (EventAction) -> Unit,
    startTime: Int,
    endTime: Int,
    taskName: String,
    taskColor: Color
) {
    val period = startTime.toDayPeriod()
    val duration = (endTime - startTime).toDuration()


    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)) {
            TextField(
                value = taskName,
                onValueChange = {onAction(EventAction.OnTaskTitleChange(it))},
                placeholder = { Text("Task Name") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(color = taskColor, shape = CircleShape)
                )
                Text(
                    text = period,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Text3
                )
                Text(
                    text = "·",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Text3
                )
                Text(
                    text = duration,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Text3
                )
            }
        }
    }
}
