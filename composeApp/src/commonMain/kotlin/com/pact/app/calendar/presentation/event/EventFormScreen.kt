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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pact.app.icons.add_2
import com.pact.app.icons.close
import com.pact.app.Primary
import com.pact.app.Text1
import com.pact.app.Text3
import com.pact.app.calendar.presentation.event.components.EventColorPicker
import com.pact.app.calendar.presentation.event.components.EventDateCard
import com.pact.app.calendar.presentation.event.components.EventDeleteButton
import com.pact.app.calendar.presentation.event.components.EventDurationChips
import com.pact.app.calendar.presentation.event.components.EventNotesCard
import com.pact.app.calendar.presentation.event.components.EventSettingsCard
import com.pact.app.calendar.presentation.event.components.EventTaskNameCard
import com.pact.app.calendar.presentation.event.components.EventTimeCard
import com.pact.app.calendar.presentation.event.sheets.BottomSheetType
import com.pact.app.core.domain.PactEvent
import com.pact.app.core.ui.LinkText
import com.pact.app.icons.arrow_right_alt
import com.pact.app.icons.chevron_left
import com.pact.app.calendar.presentation.event.sheets.DatePickerSheet
import com.pact.app.calendar.presentation.event.sheets.ReminderSheet
import com.pact.app.calendar.presentation.event.sheets.RepeatSheet
import com.pact.app.calendar.presentation.event.sheets.TimePickerSheet
import jdk.jfr.Event


@Composable
fun EventFormScreenRoot(
    viewModel: EventViewModel,
    eventId: String?,
    onBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(eventId) {
        if (eventId != null) {
            viewModel.onAction(EventAction.LoadEvent(eventId))
        }
    }

    LaunchedEffect(state.saveSuccess) {
        if (state.saveSuccess) {
            onBack()
            viewModel.onAction(EventAction.ResetSaveState)
        }
    }

    LaunchedEffect(state.deleteSuccess) {
        if (state.deleteSuccess) {
            onBack()
            viewModel.onAction(EventAction.ResetDeleteState)
        }
    }

    EventFormScreen(
        state = state,
        onAction = viewModel::onAction,
        onBack = onBack
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EventFormScreen(
    state: EventState,
    onAction: (EventAction) -> Unit,
    onBack: () -> Unit,
){

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded=true)
    val scope = rememberCoroutineScope()
    val sheetType = state.activeSheet

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
            { onAction(EventAction.OnSaveEventFormScreen) },
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
                onTaskTitleChange = {onAction(EventAction.OnTaskTitleChange(it))}
            )

            Text(
                text = "WHEN",
                style = MaterialTheme.typography.labelSmall,
                color = Text3,
                letterSpacing = 1.5.sp,
                modifier = Modifier.padding(start = 4.dp, top = 16.dp, bottom = 8.dp)
            )

            EventDateCard(
                todayDate = state.todayDate,
                date = state.date,
                onClick = {onAction(EventAction.OpenSheet(BottomSheetType.DateSheet))}
            )

            Spacer(modifier = Modifier.height(12.dp))

            EventTimeCard(
                startTime = state.startTime,
                endTime = state.endTime,
                onStartTimeClick = { onAction(EventAction.OpenSheet(BottomSheetType.TimeSheetStartTime)) },
                onEndTimeClick = {  onAction(EventAction.OpenSheet(BottomSheetType.TimeSheetEndTime))}
            )

            Spacer(modifier = Modifier.height(20.dp))


            EventDurationChipsHeader()
            EventDurationChips(
                selectedDuration = (state.endTime - state.startTime),
                onDurationSelected = { duration ->
                    if (duration != null) {
                        onAction(EventAction.OnDurationSelected(duration))
                    } else {
//                        onAction(EventAction.OnCustomDurationRequested) // opens time sheet instead
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            EventSettingsCard(
                labelOnReminder = state.reminder.type.label,
                labelOnRepeat = state.repeat.label,
                onReminderClick = { onAction(EventAction.OpenSheet(BottomSheetType.ReminderSheet)) },
                onRepeatClick = {onAction(EventAction.OpenSheet(BottomSheetType.RepeatSheet))}
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "COLOR TAG",
                style = MaterialTheme.typography.labelSmall,
                color = Text3,
                letterSpacing = 1.5.sp,
                modifier = Modifier.padding(start = 4.dp, top = 16.dp, bottom = 8.dp)
            )

            EventColorPicker(
                selectedColor = state.color,
                onColorSelected = {onAction(EventAction.OnColorChange(it))}
            )

            Text(
                text = "NOTES",
                style = MaterialTheme.typography.labelSmall,
                color = Text3,
                letterSpacing = 1.5.sp,
                modifier = Modifier.padding(start = 4.dp, top = 16.dp, bottom = 8.dp)
            )

            EventNotesCard(
                notes = state.notes ?: "",
                onNotesChange = {onAction(EventAction.OnNotesChange(it))}
            )

            Spacer(modifier = Modifier.height(8.dp))

            if(!state.isAddMode){
                EventDeleteButton { onAction(EventAction.OnDeleteEventFormScreen) }
            }


        }

        // Pinned button
        FloatingActionButton(
            onClick = {onAction(EventAction.OnSaveEventFormScreen)},
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
                if(state.isAddMode){
                    Icon(imageVector = add_2, contentDescription = "Add event")
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = if(state.isAddMode) "Add to my day" else "Save Changes",
                    color = Color.White
                )
            }
        }
    }

    if(sheetType != null) {
        ModalBottomSheet(
            onDismissRequest = {onAction(EventAction.CloseSheet)},
            sheetState = sheetState
        ){
            when(sheetType){
                is BottomSheetType.DateSheet -> DatePickerSheet(
                    selected = state.date,
                    onConfirm = {date -> onAction(EventAction.ConfirmDate(date))}
                )
                is BottomSheetType.TimeSheetStartTime -> TimePickerSheet(
                    initialTime = state.startTime,
                    isStartTime = true,
                    onConfirm = {minutes -> onAction(EventAction.ConfirmTime(minutes, isStartTime = true))},
                )
                is BottomSheetType.TimeSheetEndTime -> TimePickerSheet(
                    initialTime = state.endTime,
                    isStartTime = false,
                    onConfirm = {minutes -> onAction(EventAction.ConfirmTime(minutes, isStartTime = false))},
                )
                is BottomSheetType.ReminderSheet -> ReminderSheet(
                    selected = state.reminder,
                    onConfirm = {reminderTime -> onAction(EventAction.ConfirmReminder(reminderTime))}
                )
                is BottomSheetType.RepeatSheet -> RepeatSheet(
                    selected = state.repeat,
                    onConfirm = {repeatType -> onAction(EventAction.ConfirmRepeat(repeatType))}
                )
            }

        }
    }
}

@Composable
private fun EventFormHeader(
    modifier: Modifier = Modifier,
    isAddMode: Boolean,
    onSave: () -> Unit,
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
        LinkText("Save", onSave)
    }
}


@Composable
private fun EventDurationChipsHeader(){
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
}