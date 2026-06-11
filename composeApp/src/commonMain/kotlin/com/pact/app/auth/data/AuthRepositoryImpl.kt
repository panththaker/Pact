package com.pact.app.auth.data
import com.pact.app.auth.domain.AuthRepository
import com.pact.app.core.domain.SessionManager
import com.pact.app.core.domain.UserSession

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val sessionManager: SessionManager
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<UserSession> {
        return try {
            val userSession = authRemoteDataSource.login(email, password)?.toDomain()
                ?: return Result.failure(Exception("Login failed"))
            sessionManager.setSession(userSession)
            Result.success(userSession)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(email: String, password: String, firstName: String): Result<UserSession> {
        return try {
            val userSession = authRemoteDataSource.signup(email, password, firstName)?.toDomain()
                ?: return Result.failure(Exception("Signup failed"))
            sessionManager.setSession(userSession)
            Result.success(userSession)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCurrentUser(): UserSession? {
        return try {
            authRemoteDataSource.getCurrentUser()?.toDomain()
        } catch (e: Exception) {
            null
        }
    }
}