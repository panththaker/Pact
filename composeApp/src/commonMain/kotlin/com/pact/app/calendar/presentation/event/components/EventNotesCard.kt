package com.pact.app.calendar.presentation.event.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pact.app.SurfaceWash
import com.pact.app.Text3

@Composable
fun EventNotesCard(
    notes: String,
    onNotesChange: (String) -> Unit
) {
    TextField(
        value = notes,
        onValueChange = onNotesChange,
        placeholder = {
            Text(
                text = "Add a note...",
                style = MaterialTheme.typography.bodyLarge,
                color = Text3
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SurfaceWash,
            unfocusedContainerColor = SurfaceWash,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp),
        maxLines = 4
    )
}