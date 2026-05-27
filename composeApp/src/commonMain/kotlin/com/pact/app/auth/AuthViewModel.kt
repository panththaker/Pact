package com.pact.app.auth
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()


    fun onEmailChange(email: String){
        _state.update {it.copy(email=email) }
    }

    fun onPasswordChange(password: String){
        _state.update {it.copy(password=password) }
    }

    fun onSignUp(){
        viewModelScope.launch {
            _state.update {it.copy(isLoading = true) }
            // Supabase call goes here
        }
    }

    fun onLogin(){
        viewModelScope.launch {
            _state.update {it.copy(isLoading = true) }
            // Supabase call goes here
        }
    }

    fun clearError(){
        _state.update {it.copy(errorMessage = null) }
    }


}
