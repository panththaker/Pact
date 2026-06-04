package com.pact.app.auth.presentation

data class AuthState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false, // For delay on supabase
    val errorMessage: String? = null, // Error message from supabase
    val isLoggedIn: Boolean = false // Once succesfully logged in can move to next page
)