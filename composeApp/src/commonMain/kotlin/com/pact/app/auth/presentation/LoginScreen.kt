package com.pact.app.auth.presentation
import androidx.compose.foundation.background
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.pact.app.core.ui.PactAvatar
import com.pact.app.Primary
import com.pact.app.SurfaceSoft
import com.pact.app.SurfaceWash
import com.pact.app.Text1
import com.pact.app.Text2
import com.pact.app.Text3
import org.jetbrains.compose.resources.painterResource
import pact.composeapp.generated.resources.Res
import pact.composeapp.generated.resources.bootstrap_arrow_left_square
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.OutlinedButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.ui.text.font.FontWeight
import pact.composeapp.generated.resources.ic_google
import androidx.compose.runtime.LaunchedEffect


@Composable
fun LoginScreenRoot(
    viewModel: AuthViewModel,
    onBack: () -> Unit,
    onSignupClick: () -> Unit,
    navToCalendar: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            navToCalendar()
        }
    }

    LoginScreen(
        state = state,
        onAction = viewModel::onAction,
        onBack = onBack,
        onSignupClick = onSignupClick

    )


}


@Composable
private fun LoginScreen(
    state: AuthState,
    onAction: (AuthAction) -> Unit,
    onBack: () -> Unit,
    onSignupClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
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

        Spacer(modifier = Modifier.height(25.dp))

        PactAvatar(72.dp)

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Welcome back.",
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Let's pick up right where you left off.",
            style = MaterialTheme.typography.bodyLarge,
            color = Text2
        )

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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("Password")
                Text( // Todo make a forget page
                    text="Forgot?",
                    color = Primary,
                    style = MaterialTheme.typography.labelSmall
                )
            }
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
                placeholder = {
                    Text(
                        text = "Password",
                        color = Text3,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                shape = RoundedCornerShape(12.dp),
            )

            Spacer(modifier = Modifier.height(10.dp))

            // TODO: Make a checkbox for remmeber this device

            // Login Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ){
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                    ,
                    onClick = {onAction(AuthAction.OnLoginClick)},
                    shape = RoundedCornerShape(18.dp)

                ) {
                    Text(
                        text = "Log In",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            // OR divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Text3.copy(alpha = 0.3f)
                )
                Text(
                    text = "OR",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = Text3
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Text3.copy(alpha = 0.3f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = {onAction(AuthAction.OnGoogleSignInClick)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(1.dp, Text3.copy(alpha = 0.3f))
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_google),
                        contentDescription = "Google",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Continue with Google",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Text1
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text="New to Pact? ",
                    style = MaterialTheme.typography.labelSmall

                )
                Text(
                    modifier = Modifier.clickable(){onSignupClick()},
                    text="Create an Account",
                    style= MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = Primary
                )
            }


        }

    }
}