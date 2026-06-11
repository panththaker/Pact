package com.pact.app.auth.data

import com.pact.app.core.domain.UserSession
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class UserDto(
    @SerialName("id") val id: String,
    @SerialName("email") val email: String,
    @SerialName("first_name") val firstName: String
) {
    fun toDomain() = UserSession(
        userId = id,
        firstName = firstName
    )
}