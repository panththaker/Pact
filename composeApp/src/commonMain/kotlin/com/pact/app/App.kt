package com.pact.app
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.pact.app.calendar.presentation.event.EventFormScreenRoot
import com.pact.app.calendar.presentation.event.EventViewModel
import com.pact.app.core.domain.PactEvent
import com.pact.app.core.domain.RepeatType
import kotlinx.datetime.LocalDate
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ScreenPreview(){
    PactTheme{
        val samplePactEvent = PactEvent(
            id = "test-123",
            title = "Deep focus: calendar mocks",
            date = LocalDate(2026, 6, 16),
            startTime = 570,  // 9:30 AM
            endTime = 660,    // 11:00 AM
            color = 0xFF9B8EC4,
            reminder = 10,
            repeat = RepeatType.NONE,
            notes = "Work on the week view grid",
            isCompleted = false
        )
        val viewModel = koinViewModel<EventViewModel>()
        EventFormScreenRoot(viewModel, samplePactEvent, {})
    }
}

@Composable
@Preview
fun App() {
//    PactTheme {
//        Navigation()
//    }

    ScreenPreview()
}


