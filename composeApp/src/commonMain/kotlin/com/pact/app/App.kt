package com.pact.app

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pact.app.auth.OpeningScreen
import com.pact.app.auth.SignUpScreen

@Composable
fun ScreenPreview(){
    PactTheme{
        SignUpScreen (onBack = {} )
    }
}

@Composable
@Preview
fun App() {
//    PactTheme {
//        val navController = rememberNavController()
//        NavHost(
//            navController = navController,
//            startDestination = Opening
//        ){
//            composable<Opening>{
//                OpeningScreen(onGetStarted = {navController.navigate(SignUp)})
//            }
//            composable<SignUp> {
//                SignUpScreen(onBack = {navController.popBackStack()})
//            }
//        }
//    }

    ScreenPreview()
}


