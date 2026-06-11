package com.pact.app.auth.data
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    val id: String,
    val first_name: String
)