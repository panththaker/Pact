package com.pact.app.calendar.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pact.app.PactAvatar
import com.pact.app.Primary
import com.pact.app.SurfaceWash
import com.pact.app.Text3
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.getValue
import com.pact.app.calendar.presentation.views.day.DayView
import com.pact.app.calendar.presentation.views.month.MonthView
import com.pact.app.calendar.presentation.views.week.WeekView

@Composable
fun CalendarScreenRoot(
    viewModel: CalendarViewModel
){
    val state by viewModel.state.collectAsState()
    CalendarScreen(
        state = state,
        onAction = viewModel::onAction
    )
}


@Composable
private fun CalendarScreen(
    state: CalendarState,
    onAction:(CalendarAction) -> Unit
)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        Header()
        Spacer(modifier = Modifier.height(5.dp))
        ViewSelector(
            selectedView = state.selectedView,
            onViewSelected = { onAction(CalendarAction.OnSelectedViewChange(it)) }
        )
        when (state.selectedView) {
            CalendarView.MONTH -> MonthView()
            CalendarView.WEEK -> WeekView()
            CalendarView.DAY -> DayView()
        }
    }
}

@Composable
private fun Header() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        PactAvatar(size = 40.dp)

        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = "Good morning, Sarah.", // TODO: Update with the user name
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Tue, May 12 · Month view", // TOOD: Update with today date
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ViewSelector(
    selectedView: CalendarView,
    onViewSelected: (CalendarView) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    )
    {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(
                    color = SurfaceWash,
                    shape = RoundedCornerShape(50)
                )
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
            ){
                CalendarView.entries.forEach { view ->
                    val isSelected = view == selectedView

                    val bgColor by animateColorAsState(
                        targetValue = if (isSelected) Primary else Color.Transparent,
                        animationSpec = tween(durationMillis = 200)
                    )
                    val textColor by animateColorAsState(
                        targetValue = if (isSelected) Color.White else Text3,
                        animationSpec = tween(durationMillis = 200)
                    )


                    Row(
                        horizontalArrangement = Arrangement.Center,  // ← centers the text
                        modifier = Modifier

                            .clip(RoundedCornerShape(50))
                            .background(bgColor)
                            .clickable { onViewSelected(view) }
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = view.name.lowercase().replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                            color = textColor
                        )
                    }
                }

            }

        }
    }
}
