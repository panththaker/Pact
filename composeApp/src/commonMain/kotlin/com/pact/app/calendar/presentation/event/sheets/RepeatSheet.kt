package com.pact.app.calendar.presentation.event.sheets

import androidx.compose.runtime.Composable
import com.pact.app.core.domain.RepeatType
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pact.app.Primary
import com.pact.app.SurfaceWash
import com.pact.app.Text1
import com.pact.app.Text2
import com.pact.app.core.domain.ReminderTime
import com.pact.app.core.domain.ReminderType
import com.pact.app.core.ui.LinkText
import com.pact.app.icons.check

@Composable
fun RepeatSheet(
    selected: RepeatType,
    onConfirm: (RepeatType) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Reminder",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Text1
            )
            LinkText("Done", onClick = {
                onConfirm(selected)
            })
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(0.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            RepeatType.entries.forEach { type ->
                val isSelected = selected == type
                Surface(
                    onClick = { onConfirm(type) },
                    color = if (isSelected) SurfaceWash else Color.White
                ) {
                    ListItem(
                        headlineContent = {
                            Text(
                                text = type.label,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Text1 else Text2
                            )
                        },
                        trailingContent = {
                            if (isSelected) {
                                Icon(
                                    imageVector = check,
                                    contentDescription = "Selected",
                                    tint = Primary
                                )
                            }
                        },
                        colors = ListItemDefaults.colors(
                            containerColor = Color.Transparent
                        )
                    )
                }
                if (type != RepeatType.entries.last()) {
                    HorizontalDivider(color = SurfaceWash)
                }
            }
        }
    }

}