package com.libertytech.tinderlike.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.libertytech.tinderlike.usecases.LoginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUIState())
    private val loginUseCase: LoginUseCase = LoginUseCase()
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()


    fun login(email: String, password: String){
        CoroutineScope(Dispatchers.IO).launch {
            _uiState.value = LoginUIState(loginUseCase.execute(email, password))
        }
    }

}

data class LoginUIState(val init: Boolean = false)