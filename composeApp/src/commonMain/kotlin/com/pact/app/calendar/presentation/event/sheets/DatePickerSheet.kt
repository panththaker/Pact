package com.pact.app.calendar.presentation.event.sheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pact.app.Primary
import com.pact.app.SurfaceWash
import com.pact.app.Text1
import com.pact.app.core.ui.LinkText
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun DatePickerSheet(
    selected: LocalDate,
    onConfirm: (LocalDate) -> Unit,
) {
    val initialMillis = selected.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialMillis
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Date",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Text1
            )
            LinkText("Done", onClick = {
                val millis = datePickerState.selectedDateMillis
                val confirmedDate = if (millis != null) {
                    Instant.fromEpochMilliseconds(millis)
                        .toLocalDateTime(TimeZone.UTC)
                        .date
                } else {
                    selected
                }
                onConfirm(confirmedDate)
            })
        }

        DatePicker(
            state = datePickerState,
            showModeToggle = false,
            title = null,
            headline = null,
            colors = DatePickerDefaults.colors(
                containerColor = SurfaceWash,
                selectedDayContainerColor = Primary,
                selectedDayContentColor = Color.White,
                todayContentColor = Primary,
                todayDateBorderColor = Primary,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}