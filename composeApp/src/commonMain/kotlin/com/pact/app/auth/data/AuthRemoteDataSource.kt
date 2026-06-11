package com.pact.app.auth.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
class AuthRemoteDataSource(private val supabaseClient: SupabaseClient){
    suspend fun signup(userEmail: String, userPassword: String, userFirstName: String): UserDto?{
        val user = supabaseClient.auth.signUpWith(Email){
            email = userEmail
            password = userPassword
            data = buildJsonObject {
                put("display_name", userFirstName)
            }
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

    suspend fun login(userEmail: String, userPassword: String): UserDto?{
        supabaseClient.auth.signInWith(Email){
            email = userEmail
            password = userPassword
        }
        val user = supabaseClient.auth.currentUserOrNull()
        if(user != null){
            val profile = supabaseClient.from("profiles").select(columns = Columns.list("id", "first_name")) {
                filter {
                    eq("id", user.id)
                }
            }.decodeSingle<ProfileDto>()
            return UserDto(
                id=user.id,
                email = userEmail,
                firstName = profile.first_name
            )
        }
        return null
    }

    suspend fun getCurrentUser(): UserDto?{
        // Supabase call
        return null
    }
}