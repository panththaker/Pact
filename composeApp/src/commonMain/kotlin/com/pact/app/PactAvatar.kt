package com.pact.app

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PactAvatar(size: Dp = 112.dp) {
    val sizePx = with(LocalDensity.current) { size.toPx() }

    Canvas(modifier = Modifier.size(size)) {
        val s = sizePx

        // Background orb
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFE8DCFF),
                    Color(0xFFB395EC),
                    Color(0xFF7C5CD9)
                ),
                center = Offset(s * 0.32f, s * 0.28f),
                radius = s * 0.85f
            ),
            radius = s / 2,
            center = Offset(s / 2, s / 2)
        )

        // Highlight glint
        drawOval(
            color = Color.White.copy(alpha = 0.55f),
            topLeft = Offset(s * 0.18f, s * 0.19f),
            size = Size(s * 0.28f, s * 0.18f)
        )

        // Left eye
        drawCircle(
            color = Color(0xFF2A1340),
            radius = s * 0.045f * 1.6f,
            center = Offset(s * 0.36f, s * 0.45f)
        )

        // Right eye
        drawCircle(
            color = Color(0xFF2A1340),
            radius = s * 0.045f * 1.6f,
            center = Offset(s * 0.64f, s * 0.45f)
        )

        // Smile
        val path = Path().apply {
            moveTo(s * 0.38f, s * 0.62f)
            quadraticBezierTo(s * 0.5f, s * 0.72f, s * 0.62f, s * 0.62f)
        }
        drawPath(
            path = path,
            color = Color(0xFF2A1340),
            style = Stroke(width = s * 0.05f, cap = StrokeCap.Round)
        )

        // Left cheek
        drawCircle(
            color = Color(0xFFFFB8D9).copy(alpha = 0.5f),
            radius = s * 0.06f,
            center = Offset(s * 0.24f, s * 0.58f)
        )

        // Right cheek
        drawCircle(
            color = Color(0xFFFFB8D9).copy(alpha = 0.5f),
            radius = s * 0.06f,
            center = Offset(s * 0.76f, s * 0.58f)
        )
    }
}