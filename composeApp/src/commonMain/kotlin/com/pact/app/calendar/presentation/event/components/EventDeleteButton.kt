package com.pact.app.calendar.presentation.event.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pact.app.icons.delete

@Composable
fun EventDeleteButton(
    onDeleteTask: () -> Unit
){
    OutlinedButton(
        onClick = { onDeleteTask },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.dp, Color.Transparent),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            contentColor = Color.Red
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(imageVector = delete, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Delete task", fontWeight = FontWeight.SemiBold)
    }
}