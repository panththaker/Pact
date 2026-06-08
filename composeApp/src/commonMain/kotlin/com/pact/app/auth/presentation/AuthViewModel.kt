package com.pact.app.auth.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pact.app.auth.domain.AuthRepository
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
                _state.update {it.copy(password=action.firstName) }
            }

            is AuthAction.OnLoginClick -> {
                viewModelScope.launch {
                    _state.update {it.copy(isLoading = true) }
                    _state.update {it.copy(isLoggedIn = true) } // TODO: For debug make it work for supabase
                }
            }

            is AuthAction.OnSignUpClick -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    _state.update {it.copy(isLoggedIn = true) }
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
