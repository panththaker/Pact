package com.pact.app.calendar.presentation.event.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import com.pact.app.core.domain.EventColors


@Composable
fun EventColorPicker(
    selectedColor: Long,
    onColorSelected: (Long) -> Unit
){
    Row(
        modifier = Modifier.selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        EventColors.all.forEach { colorLong ->
            EventColor(
                color = Color(colorLong),
                isSelected = selectedColor == colorLong,
                onColorSelected = { onColorSelected(colorLong) }
            )
        }
    }

}


@Composable
private fun EventColor(
    color: Color,
    isSelected: Boolean,
    onColorSelected: (Color) -> Unit,
){
    IconButton(
        onClick = { onColorSelected(color) },
        modifier = Modifier
            .size(48.dp)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) color else color.copy(alpha = 0.3f),
                shape = CircleShape
            )
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(color)
        )
    }
}
