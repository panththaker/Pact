package com.pact.app

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pact.app.auth.AuthViewModel
import com.pact.app.auth.OpeningScreen
import com.pact.app.auth.SignUpScreen
import com.pact.app.auth.LoginScreen

@Composable
fun ScreenPreview(){
    PactTheme{
        val viewModel = viewModel<AuthViewModel>();

//        LoginScreen(
//            viewModel=viewModel,
//            onBack = {}
//        )
//        SignUpScreen(
//            viewModel=viewModel,
//            onBack = {}
//        )
    }
}

@Composable
@Preview
fun App() {
    PactTheme {
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
                val viewModel = viewModel<AuthViewModel>();
                SignUpScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onLoginClick = {navController.navigate(Login)}
                )
            }
            composable<Login> {
                val viewModel = viewModel<AuthViewModel>();
                LoginScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onSignupClick = {navController.navigate(SignUp)}
                )
            }


        }
    }

//    ScreenPreview()
}


