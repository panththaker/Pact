package com.pact.app.auth

data class AuthState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false, // For delay on supabase
    val errorMessage: String? = null, // Error message from supabase
    val isSuccess: Boolean = false // Once succesful can handle
)