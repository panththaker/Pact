package com.pact.app.calendar.presentation.event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pact.app.icons.add_2
import com.pact.app.icons.close
import com.pact.app.Primary
import com.pact.app.Text3
import com.pact.app.calendar.presentation.event.components.EventDateCard
import com.pact.app.calendar.presentation.event.components.EventDurationChips
import com.pact.app.calendar.presentation.event.components.EventSettingsCard
import com.pact.app.calendar.presentation.event.components.EventTaskNameCard
import com.pact.app.calendar.presentation.event.components.EventTimeCard
import com.pact.app.core.domain.PactEvent
import com.pact.app.core.ui.LinkText
import com.pact.app.icons.chevron_left


@Composable
fun EventFormScreenRoot(
    viewModel: EventViewModel,
    pactEvent: PactEvent?,

    onBack: () -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(pactEvent) {
        if (pactEvent != null) {
            viewModel.OnAction(EventAction.PopulateForm(pactEvent))
        }
    }
    EventFormScreen(
        state=state,
        onAction = viewModel::OnAction,
        onBack=onBack
    )
}


@Composable
private fun EventFormScreen(
    state: EventState,
    onAction: (EventAction) -> Unit,
    onBack: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
    ) {
        // Pinned header
        EventFormHeader(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            state.isAddMode,
            onBack
        )

        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 80.dp, bottom = 100.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
        ){
            EventTaskNameCard(
                startTime = state.startTime,
                endTime = state.endTime,
                taskName = state.title,
                taskColor = Color(state.color),
                onAction = onAction
            )

            Text(
                text = "WHEN",
                style = MaterialTheme.typography.labelSmall,
                color = Text3,
                letterSpacing = 1.5.sp,
                modifier = Modifier.padding(start = 4.dp, top = 16.dp, bottom = 8.dp)
            )

            EventDateCard(
                state.todayDate,
                state.date,
                {}
            )

            Spacer(modifier = Modifier.height(12.dp))

            EventTimeCard(
                state.startTime,
                state.endTime,
                false,
                false,
                {}
            )

            Spacer(modifier = Modifier.height(12.dp))

            EventDurationChips(
                90, {}
            )

            Spacer(modifier = Modifier.height(12.dp))

            EventSettingsCard()

            Spacer(modifier = Modifier.height(12.dp))


        }

        // Pinned button
        FloatingActionButton(
            onClick = {},
            shape = RoundedCornerShape(50),
            containerColor = Primary,
            contentColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Icon(imageVector = add_2, contentDescription = "Add event")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Add to my day", color = Color.White)
            }
        }
    }
}

@Composable
private fun EventFormHeader(
    modifier: Modifier = Modifier,
    isAddMode: Boolean,
    onBack: () -> Unit
){

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        FilledTonalIconButton(
            shape = RoundedCornerShape(12.dp),
            onClick = { onBack() },
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        )
        {
            if(isAddMode){
                Icon(imageVector = close, contentDescription = "Close")
            }
            else{
                Icon(imageVector = chevron_left, contentDescription = "Go Back")
            }
        }
        Text(
            text= if(isAddMode) "New Task" else "Edit Task",
            style = MaterialTheme.typography.titleMedium
        )
        LinkText("Save", {})
    }
}
