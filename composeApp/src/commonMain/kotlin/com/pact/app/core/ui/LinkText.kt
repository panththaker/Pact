package com.pact.app.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pact.app.Primary
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LinkText(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = Primary,
        fontWeight = FontWeight(600),
        modifier = Modifier.clickable() { onClick() }
    )
}