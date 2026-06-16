package com.pact.app.calendar.presentation.event.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import com.pact.app.icons.calendar_today
import com.pact.app.Lavender100
import com.pact.app.Primary
import com.pact.app.Text1
import com.pact.app.Text3
import com.pact.app.core.utils.formatLong
import com.pact.app.icons.chevron_right
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

@Composable
fun EventDateCard(
    todayDate: LocalDate,
    date: LocalDate,
    onClick: () -> Unit
){
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        val mon = date.month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }

        val resultDate = when(date){
            todayDate.minus(1, DateTimeUnit.DAY) -> "Yesterday, $mon ${date.day}"
            todayDate -> "Today, $mon ${date.day}"
            todayDate.plus(1, DateTimeUnit.DAY) -> "Tomorrow, $mon ${date.day}"
            else -> date.formatLong()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Lavender100)
                        .padding(6.dp)

                ){
                    Icon(
                        imageVector = calendar_today,
                        contentDescription = "Calendar Icon",
                        tint = Primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(18.dp))
                Column {
                    Text(
                        text = "Date",
                        style = MaterialTheme.typography.labelMedium,
                        color = Text3
                    )
                    Text(
                        text = resultDate,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Text1
                    )
                }
            }
            Icon(
                imageVector = chevron_right,
                contentDescription = "Open Date Picker",
                tint = Text3
            )
        }
    }
}