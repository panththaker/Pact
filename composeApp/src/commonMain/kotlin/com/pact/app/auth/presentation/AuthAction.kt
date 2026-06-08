package com.pact.app.auth.presentation

sealed interface AuthAction {
    data class OnEmailChange(val email: String): AuthAction
    data class OnPasswordChange(val password: String): AuthAction
    data class OnFirstNameChange(val firstName: String): AuthAction

    data object OnLoginClick: AuthAction
    data object OnSignUpClick: AuthAction
    data object OnGoogleSignInClick: AuthAction
}