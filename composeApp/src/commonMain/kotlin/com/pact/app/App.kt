package com.pact.app

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.pact.app.auth.OpeningScreen

@Composable
@Preview
fun App() {
    PactTheme {
        OpeningScreen()
    }
}
