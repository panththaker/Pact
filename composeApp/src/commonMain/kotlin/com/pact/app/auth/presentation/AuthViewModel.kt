package com.pact.app.auth.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pact.app.auth.domain.AuthRepository
import com.pact.app.core.domain.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository): ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun onAction(action: AuthAction){
        when(action){
            is AuthAction.OnEmailChange -> {
                _state.update {it.copy(email=action.email) }
            }
            is AuthAction.OnPasswordChange -> {
                _state.update {it.copy(password=action.password) }
            }
            is AuthAction.OnFirstNameChange -> {
                _state.update {it.copy(firstName =action.firstName) }
            }

            is AuthAction.OnLoginClick -> {
                _state.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    val result: Result<UserSession> = authRepository.login(
                        email = _state.value.email,
                        password = _state.value.password)
                    result.fold(
                        onSuccess = { user ->
                            _state.update { it.copy(isLoggedIn = true) }
                        },
                        onFailure = { error ->
                            println("Login error: ${error.message}")
                            _state.update { it.copy(errorMessage = error.message) }
                        }
                    )
                    _state.update { it.copy(isLoading = false) }
                }
            }

            is AuthAction.OnSignUpClick -> {
                _state.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    val result: Result<UserSession> = authRepository.signUp(
                        email = _state.value.email,
                        password = _state.value.password,
                        firstName = _state.value.firstName)
                    result.fold(
                        onSuccess = { user ->
                            _state.update { it.copy(isLoggedIn = true) }
                        },
                        onFailure = { error ->
                            println("Signup error: ${error.message}")
                            _state.update { it.copy(errorMessage = error.message) }
                        }
                    )
                    _state.update { it.copy(isLoading = false) }
                }
            }

            is AuthAction.OnGoogleSignInClick -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }

}
