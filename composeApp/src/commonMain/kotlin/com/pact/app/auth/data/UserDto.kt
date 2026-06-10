package com.pact.app.auth.data

import com.pact.app.auth.domain.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

// TODO: Update the serial names once u get the info from supabase
data class UserDto(
    @SerialName("id") val id: String,
    @SerialName("email") val email: String,
    @SerialName("first_name") val firstName: String
) {
    fun toDomain() = User(
        id = id,
        email = email,
        firstName = firstName
    )
}