package com.pact.app.auth.domain

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun signUp(email: String, password:String, firstName:String): Result<User>
    suspend fun getCurrentUser(): User?
}
