package com.pact.app.calendar.presentation.event.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pact.app.Lavender100
import com.pact.app.Primary
import com.pact.app.Text1
import com.pact.app.Text3
import com.pact.app.icons.add_alert
import com.pact.app.icons.chevron_right
import com.pact.app.icons.repeat

// Reminder and Repeat Settings for the Add Task/Edit Task Screen

@Composable
fun EventSettingsCard(
    labelOnReminder: String,
    onReminderClick: () -> Unit,
    onRepeatClick: () -> Unit,
){
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        // Reminder Card
        SettingsCard(
            icon = add_alert,
            label = "Reminder",
            value = labelOnReminder,
            onClick = { onReminderClick() }
        )

        // Repeat Card
        SettingsCard(
            icon = repeat,
            label = "Repeat",
            value = "Doesn't repeat",
            onClick = { onRepeatClick()}
        )
    }
}

@Composable
private fun SettingsCard(
    icon: ImageVector,
    label: String,
    value: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Lavender100)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = Primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Column {
                    Text(text = label, style = MaterialTheme.typography.labelMedium, color = Text3)
                    Text(text = value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = Text1)
                }
            }
            Icon(imageVector = chevron_right, contentDescription = null, tint = Text3, modifier = Modifier.size(18.dp))
        }
    }
}
