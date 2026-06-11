package com.pact.app.auth.domain

import com.pact.app.core.domain.UserSession

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<UserSession>
    suspend fun signUp(email: String, password:String, firstName:String): Result<UserSession>
    suspend fun getCurrentUser(): UserSession?
}
