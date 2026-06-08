package com.pact.app.auth.data

class AuthRemoteDataSource {
    suspend fun signup(email: String, password: String, firstName: String): UserDto?{
        // Supabse call
        return null
    }

    suspend fun login(email: String, password: String): UserDto?{
        // Supabase call
        return null
    }

    suspend fun getCurrentUser(): UserDto?{
        // Supabase call
        return null
    }
}