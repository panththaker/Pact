package com.pact.app
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.pact.app.calendar.presentation.CalendarScreenRoot


@Composable
fun ScreenPreview(){
    PactTheme{
        CalendarScreenRoot();
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


