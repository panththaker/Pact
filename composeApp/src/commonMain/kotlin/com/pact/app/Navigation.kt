package com.pact.app

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pact.app.auth.presentation.AuthViewModel
import com.pact.app.auth.presentation.LoginScreenRoot
import com.pact.app.auth.presentation.OpeningScreen
import com.pact.app.auth.presentation.SignUpScreenRoot
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable object Opening
@Serializable object SignUp

@Serializable object Login{}


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Opening,
        enterTransition = { fadeIn(animationSpec = tween(150)) },
        exitTransition = { fadeOut(animationSpec = tween(150)) },
        popEnterTransition = { fadeIn(animationSpec = tween(150)) },
        popExitTransition = { fadeOut(animationSpec = tween(150)) }

    ){
        composable<Opening>{
            OpeningScreen(
                onGetStarted = {navController.navigate(SignUp)},
                onLoginClick = {navController.navigate(Login)}

            )
        }
        composable<SignUp> {
            val viewModel = koinViewModel<AuthViewModel>()
            SignUpScreenRoot(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onLoginClick = {navController.navigate(Login)},
                navToHome = {}

            )
        }
        composable<Login> {
            val viewModel = koinViewModel<AuthViewModel>()
            LoginScreenRoot(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onSignupClick = {navController.navigate(SignUp)},
                navToHome = {}
            )
        }


    }
}