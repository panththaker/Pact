package com.pact.app.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pact.app.Primary
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LinkText(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = Primary,
            fontWeight = FontWeight(600),
        )
    }
}