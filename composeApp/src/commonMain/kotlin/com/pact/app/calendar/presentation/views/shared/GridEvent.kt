package com.pact.app.calendar.presentation.views.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pact.app.core.domain.PactEvent

@Composable
fun GridEvent(
    event: PactEvent,
    modifier: Modifier
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(event.color))
            .padding(4.dp)
    ) {
        Text(
            text = event.title,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}