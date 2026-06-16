package com.pact.app.calendar.presentation.event.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pact.app.Primary
import com.pact.app.Text1
import com.pact.app.Text3
import com.pact.app.core.utils.toDuration
import com.pact.app.icons.arrow_right_alt
import com.pact.app.icons.edit

@Composable
fun EventDurationChips(
    selectedDuration: Int?, // minutes, null if custom
    onDurationSelected: (Int?) -> Unit
) {
    val durations = listOf(15, 30, 45, 60, 90)

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "QUICK DURATION",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = Text1,
                letterSpacing = 1.5.sp
            )
            Icon(
                imageVector = arrow_right_alt,
                contentDescription = null,
                tint = Text3,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = "sets end time",
                style = MaterialTheme.typography.bodySmall,
                color = Text3
            )
        }

        // Chips
        Row(
            horizontalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            durations.forEach { duration ->
                val isSelected = selectedDuration == duration
                Card(
                    onClick = { onDurationSelected(duration) },
                    shape = RoundedCornerShape(50.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) Primary else Color.White
                    ),
                    elevation = CardDefaults.cardElevation(2.dp),
                    modifier = Modifier.wrapContentWidth()
                )  {
                    Text(
                        text = duration.toDuration(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = if (isSelected) Color.White else Text1,
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 6.dp)
                    )
                }
            }

        }
        // Custom chip
        Card(
            onClick = { onDurationSelected(null) },
            shape = RoundedCornerShape(50.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Custom",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Text1
                )
                Icon(
                    imageVector = edit,
                    contentDescription = null,
                    tint = Text3,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}