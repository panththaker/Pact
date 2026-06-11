package com.pact.app
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.pact.app.auth.presentation.AuthViewModel
import com.pact.app.calendar.presentation.CalendarScreenRoot
import com.pact.app.calendar.presentation.CalendarViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ScreenPreview(){
    PactTheme{
        val viewModel = koinViewModel<CalendarViewModel>()
        CalendarScreenRoot(viewModel, {}, {}, {});
    }
}

@Composable
@Preview
fun App() {
    PactTheme {
        Navigation()
    }

//    ScreenPreview()
}


