package com.pact.app.auth.data

import com.pact.app.auth.domain.AuthRepository
import com.pact.app.auth.domain.User

class AuthRepositoryImpl(private val authRemoteDataSource: AuthRemoteDataSource): AuthRepository{
    override suspend fun login(email: String, password: String): Result<User>{
        try {
            val userDto: UserDto? = authRemoteDataSource.login(email, password)
            val user: User? = userDto?.toDomain()

            if(user == null){
                return Result.failure(Exception("Login failed"))
            }
            return Result.success(user)
        }
        catch(e: Exception){
            return Result.failure(Exception(e))
        }

    }
    override suspend fun signUp(email: String, password:String, firstName:String): Result<User>{
        try {
            val userDto: UserDto? = authRemoteDataSource.signup(email, password, firstName)
            val user: User? = userDto?.toDomain()

            if(user == null){
                return Result.failure(Exception("Signup failed"))
            }
            return Result.success(user)
        }
        catch(e: Exception){
            return Result.failure(Exception(e))
        }


    }
    override suspend fun getCurrentUser(): User?{
        try {
            return authRemoteDataSource.getCurrentUser()?.toDomain()
        }
        catch(e: Exception){
            return null
        }
    }
}