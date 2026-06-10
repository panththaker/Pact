package com.pact.app.auth.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from

class AuthRemoteDataSource(private val supabaseClient: SupabaseClient){
    suspend fun signup(userEmail: String, userPassword: String, userFirstName: String): UserDto?{
        val user = supabaseClient.auth.signUpWith(Email){
            email = userEmail
            password = userPassword
        }
        val uuid: String? = user?.id
        if(uuid != null){
            val newUser = ProfileInsert(uuid, userFirstName)
            supabaseClient.from("profiles").insert(newUser)
            return UserDto(
                id=uuid,
                email = userEmail,
                firstName = userFirstName
            )
        }
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