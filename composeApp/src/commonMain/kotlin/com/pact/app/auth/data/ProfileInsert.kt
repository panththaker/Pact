package com.pact.app.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class ProfileInsert(
    val id: String,
    val first_name: String
)