package com.pact.app.auth.data

import com.pact.app.auth.domain.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

// TODO: Update the serial names once u get the info from supabase
data class UserDto(
    @SerialName("user_id") val id: String,
    @SerialName("email_address") val email: String,
    @SerialName("display_name") val username: String
) {
    fun toDomain() = User(
        id = id,
        email = email,
        username = username
    )
}