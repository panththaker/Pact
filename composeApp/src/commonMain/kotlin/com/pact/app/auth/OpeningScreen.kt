package com.pact.app.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pact.app.BgCanvas
import com.pact.app.PactAvatar
import com.pact.app.PactTheme
import com.pact.app.PlusJakartaSans
import com.pact.app.openingScreenBackgroundGradient

@Composable
fun OpeningScreen(onGetStarted: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = openingScreenBackgroundGradient
                )
            )
            .padding(16.dp)
            .safeContentPadding(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Section
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text="PACT",
                style= MaterialTheme.typography.labelSmall
            )
        }


        // Middle Section
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ){
            PactAvatar(size = 112.dp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text="Your day starts \n with a plan.",
                style= MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A warm, two-minute chat with Pact before the world gets loud.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )

        }

        // Bottom Section
        Column(
            modifier = Modifier.padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(50.dp)
                ,
                onClick = onGetStarted,
                shape = RoundedCornerShape(18.dp)

            ) {
                Text(
                    text = "Get Started",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text="Already have an account? ",
                    style = MaterialTheme.typography.labelSmall

                    )
                Text(
                    text="Sign in",
                    style= MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }



    }
}

@Composable
@Preview
private fun OpeningScreenPreview() {
    PactTheme {
        OpeningScreen(onGetStarted = {})
    }
}