package com.pact.app.calendar.presentation.event.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pact.app.Lavender100
import com.pact.app.Primary
import com.pact.app.SurfaceWash
import com.pact.app.Text1
import com.pact.app.Text3
import com.pact.app.core.utils.toTimeString
import com.pact.app.icons.arrow_right_alt
import com.pact.app.icons.timer

@Composable
fun EventTimeCard(
    startTime: Int,
    endTime: Int,
    startTimeSelected: Boolean,
    endTimeSelected: Boolean,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Lavender100)
                    .padding(6.dp)
            ) {
                Icon(
                    imageVector = timer,
                    contentDescription = "Timer Icon",
                    tint = Primary,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TimeSelector(
                    label = "START",
                    time = startTime,
                    isSelected = startTimeSelected,
                    onClick = onClick
                )
                Icon(
                    imageVector = arrow_right_alt,
                    contentDescription = null,
                    tint = Text3,
                    modifier = Modifier.size(20.dp)
                )
                TimeSelector(
                    label = "END",
                    time = endTime,
                    isSelected = endTimeSelected,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
fun TimeSelector(
    label: String,
    time: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val amPm = if ((time / 60) < 12) "AM" else "PM"
    val timeText = "${time.toTimeString()} $amPm"

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Primary else SurfaceWash
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier.padding(start = 18.dp, end = 36.dp, top = 12.dp, bottom = 12.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = if (isSelected) Color.White.copy(alpha = 0.7f) else Text3,
                letterSpacing = 1.5.sp
            )
            Text(
                text = timeText,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.White else Text1
            )
        }
    }
}