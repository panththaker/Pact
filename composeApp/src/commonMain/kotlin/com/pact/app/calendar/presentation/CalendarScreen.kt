package com.pact.app.calendar.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pact.app.PactAvatar
import com.pact.app.Primary
import com.pact.app.SurfaceWash
import com.pact.app.Text1
import com.pact.app.Text2
import com.pact.app.Text3
import org.jetbrains.compose.resources.painterResource
import pact.composeapp.generated.resources.Res
import pact.composeapp.generated.resources.bootstrap_arrow_left_square


@Composable
fun CalendarScreenRoot(){
    CalendarScreen()
}


@Composable
private fun CalendarScreen(){
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
        ViewSelector(CalendarView.MONTH, {})
    }
}

@Composable
private fun Header(){
    Row (){
        PactAvatar()
        Column (){
            Text("Good Morning, User")
            Text("Tue May 12 - Month View")
        }
    }
}

@Composable
private fun ViewSelector(
    selectedView: CalendarView,
    onViewSelected: (CalendarView) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = SurfaceWash,
                shape = RoundedCornerShape(50)
            )
            .padding(4.dp)
    ) {
        Row {
            CalendarView.entries.forEach { view ->
                val isSelected = view == selectedView
                Box(
                    modifier = Modifier
                        .background(
                            color = if (isSelected) Primary else Text2,
                            shape = RoundedCornerShape(50)
                        )
                        .clickable() { onViewSelected(view) }
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = view.name.lowercase().replaceFirstChar { it.uppercase() },
                        color = if (isSelected) Color.White else Text3// muted color
                    )
                }
            }

        }

    }
}
