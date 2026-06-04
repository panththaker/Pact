package com.pact.app.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pact.app.SurfaceWash
import com.pact.app.Text1
import org.jetbrains.compose.resources.painterResource
import pact.composeapp.generated.resources.Res
import pact.composeapp.generated.resources.bootstrap_arrow_left_square
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.pact.app.Primary
import com.pact.app.SurfaceSoft
import com.pact.app.Text3

@Composable
fun SignUpScreenRoot(
    viewModel: AuthViewModel,
    onBack: () -> Unit,
    onLoginClick: () -> Unit,
    navToHome: () -> Unit
){
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            navToHome()
        }
    }

    SignUpScreen(
        state = state,
        onAction = viewModel::onAction,
        onBack = onBack,
        onLoginClick = onLoginClick
    )
}

@Composable
private fun SignUpScreen(
    state: AuthState,
    onAction: (AuthAction) -> Unit,
    onBack: () -> Unit,
    onLoginClick: () -> Unit
)
{

    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .safeDrawingPadding()
        .padding(horizontal = 24.dp, vertical = 16.dp)
        .imePadding()
        .verticalScroll(rememberScrollState()),


        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        // Top Back Button
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = SurfaceWash,
                        shape = RoundedCornerShape(14.dp)
                    )
            ) {
                Icon(
                    painter = painterResource(Res.drawable.bootstrap_arrow_left_square),
                    contentDescription = "Back",
                    tint = Text1,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        // Heading Text
        Column (
            modifier = Modifier
        ){
            Text(
                text="Let's get you set up.",
                style= MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text="Just a couple of things, then we'll plan your first day together.",
                style=MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Text fields for inputting email and password
        Column (
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Email")
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.email,
                onValueChange = {newText -> onAction(AuthAction.OnEmailChange(newText))},
                placeholder = {
                    Text(
                        text = "example@gmail.com",
                        color = Text3,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = SurfaceSoft,
                    unfocusedContainerColor = SurfaceSoft,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Text1,
                    unfocusedTextColor = Text1,
                    cursorColor = Primary,
                ),

                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text("Password")
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                onValueChange = {newText -> onAction(AuthAction.OnPasswordChange(newText))},
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = SurfaceSoft,
                    unfocusedContainerColor = SurfaceSoft,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Text1,
                    unfocusedTextColor = Text1,
                    cursorColor = Primary,
                ),
                shape = RoundedCornerShape(12.dp),
            )

            Spacer(modifier = Modifier.height(10.dp))

        }

        Spacer(modifier = Modifier.weight(1f))

        // Signup Button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ){
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                ,
                onClick = {onAction(AuthAction.OnSignUpClick)},
                shape = RoundedCornerShape(18.dp)

            ) {
                Text(
                    text = "Create Account",
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
                    modifier = Modifier.clickable() {onLoginClick()},
                    text="Log in",
                    style= MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = Primary
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text="By continuing, you agree to our Terms & Privacy. ",
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
                color = Text3,

            )
        }


    }
}