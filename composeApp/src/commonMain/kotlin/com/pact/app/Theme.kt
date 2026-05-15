package com.pact.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pact.composeapp.generated.resources.Res
import pact.composeapp.generated.resources.plus_jakarta_sans_regular
import pact.composeapp.generated.resources.plus_jakarta_sans_medium
import pact.composeapp.generated.resources.plus_jakarta_sans_semibold
import pact.composeapp.generated.resources.plus_jakarta_sans_bold
import org.jetbrains.compose.resources.Font

val PlusJakartaSans
    @Composable get() = FontFamily(
        Font(Res.font.plus_jakarta_sans_regular, FontWeight.Normal),
        Font(Res.font.plus_jakarta_sans_medium, FontWeight.Medium),
        Font(Res.font.plus_jakarta_sans_semibold, FontWeight.SemiBold),
        Font(Res.font.plus_jakarta_sans_bold, FontWeight.Bold),
    )

// ── Colors ──────────────────────────────────────

val openingScreenBackgroundGradient = listOf(Color(0xFFEDE7FF), Color(0xFFFDFCFF))

val BgCanvas       = Color(0xFFECE7F5)
val Bg             = Color(0xFFFDFCFF)
val Surface        = Color(0xFFFFFFFF)
val SurfaceWash    = Color(0xFFF3EDFF)
val SurfaceSoft    = Color(0xFFF8F4FF)

val Primary        = Color(0xFF7C5CD9)
val PrimaryHover   = Color(0xFF6E4ECF)
val PrimarySoft    = Color(0xFFB09CE6)
val PrimaryTint    = Color(0xFFDED2F5)

val Lavender50     = Color(0xFFF7F2FF)
val Lavender100    = Color(0xFFECE1FB)
val Lavender200    = Color(0xFFD9C8F5)
val Lavender300    = Color(0xFFB9A1EA)
val Text1          = Color(0xFF2A1E4A)
val Text2          = Color(0xFF5D5377)
val Text3          = Color(0xFF8A829E)
val TextOnPrimary  = Color(0xFFFFFFFF)

@Composable
fun AppTypography(): Typography {
    val plusJakartaSans = FontFamily(
        Font(Res.font.plus_jakarta_sans_regular, FontWeight.Normal),
        Font(Res.font.plus_jakarta_sans_medium, FontWeight.Medium),
        Font(Res.font.plus_jakarta_sans_semibold, FontWeight.SemiBold),
        Font(Res.font.plus_jakarta_sans_bold, FontWeight.Bold),
    )
    return Typography(
        headlineLarge = TextStyle(
            fontFamily = plusJakartaSans,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Text1
        ),
        bodyLarge = TextStyle(
            fontFamily = plusJakartaSans,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Text2
        ),
        bodyMedium = TextStyle(
            fontFamily = plusJakartaSans,
            fontSize = 15.sp,
            color = Text2
        ),
        labelSmall = TextStyle(
            fontFamily = plusJakartaSans,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Text2
        )
    )
}

@Composable
fun PactTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Primary,
            onPrimary = TextOnPrimary,
            background = Bg,
            surface = Surface,
            onBackground = Text1,
            onSurface = Text1,
        ),
        typography = AppTypography(), // ← call as function now
        content = content
    )
}
