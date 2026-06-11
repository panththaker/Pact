package com.pact.app.calendar.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pact.app.core.ui.PactAvatar
import com.pact.app.Primary
import com.pact.app.SurfaceWash
import com.pact.app.Text3
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Brush
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.test.add_2
import com.pact.app.PrimaryTint
import com.pact.app.calendar.presentation.views.day.DayView
import com.pact.app.calendar.presentation.views.month.MonthView
import com.pact.app.calendar.presentation.views.week.WeekView
import com.pact.app.core.domain.UserSession
import com.pact.app.core.ui.NavTab
import com.pact.app.core.ui.PactNavBar

@Composable
fun CalendarScreenRoot(
    viewModel: CalendarViewModel,
    onNavigateToTodo: () -> Unit,
    onNavigateToChat: () -> Unit,
    onNavigateToProfile: () -> Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val session by viewModel.session.collectAsStateWithLifecycle()
    CalendarScreen(
        state = state,
        session = session,
        onAction = viewModel::onAction,
        onNavigateToTodo = onNavigateToTodo,
        onNavigateToChat = onNavigateToChat,
        onNavigateToProfile = onNavigateToProfile,
    )
}


@Composable
private fun CalendarScreen(
    state: CalendarState,
    session: UserSession?,
    onAction:(CalendarAction) -> Unit,
    onNavigateToTodo: () -> Unit,
    onNavigateToChat: () -> Unit,
    onNavigateToProfile: () -> Unit
)
{
    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(PrimaryTint.copy(alpha = 0.8f), Color.Transparent)
                        ),
                        shape = CircleShape
                    )
            ) {
                FloatingActionButton(
                    onClick = {},
                    shape = CircleShape,
                    containerColor = Primary,
                    contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp),
                    modifier = Modifier.border(
                        width = 3.dp,
                        color = Color.White,
                        shape = CircleShape
                    )
                ) {
                    Icon(
                        imageVector = add_2,
                        contentDescription = "Add event"
                    )
                }
            }
        },
        bottomBar = {
            PactNavBar(
                selectedTab = NavTab.CALENDAR,
                onTabSelected = { tab ->
                    when (tab) {
                        NavTab.CALENDAR -> {}  // already here
                        NavTab.TODO -> onNavigateToTodo()
                        NavTab.CHAT -> onNavigateToChat()
                        NavTab.PROFILE -> onNavigateToProfile()
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing

    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .imePadding()
                .then(
                    if (state.selectedView == CalendarViewType.MONTH) {
                        Modifier.verticalScroll(rememberScrollState())
                    } else {
                        Modifier
                    }
                ),
            horizontalAlignment = Alignment.Start,
        ) {
            Header(state=state, session=session)
            Spacer(modifier = Modifier.height(5.dp))
            ViewSelector(
                selectedView = state.selectedView,
                onViewSelected = { onAction(CalendarAction.OnSelectedViewChange(it)) }
            )
            Spacer(modifier = Modifier.height(15.dp))
            when (state.selectedView) {
                CalendarViewType.MONTH -> MonthView(state, onAction)
                CalendarViewType.WEEK -> WeekView(state, onAction)
                CalendarViewType.DAY -> DayView()
            }
        }

    }
}

@Composable
private fun Header(state: CalendarState, session: UserSession?) {

    val dayOfWeek = state.todayDate.dayOfWeek.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
    val month = state.todayDate.month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
    val day = state.todayDate.day
    val viewLabel = state.selectedView.name.lowercase().replaceFirstChar { it.uppercase() }

    val subtitleText = "$dayOfWeek, $month $day · $viewLabel view"

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        PactAvatar(size = 40.dp)

        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = "Good morning, ${session?.firstName}.", // TODO: Update with the user name
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = subtitleText,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ViewSelector(
    selectedView: CalendarViewType,
    onViewSelected: (CalendarViewType) -> Unit
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
                CalendarViewType.entries.forEach { view ->
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
