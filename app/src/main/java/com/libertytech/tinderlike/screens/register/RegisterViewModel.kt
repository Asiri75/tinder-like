package com.libertytech.tinderlike.screens.register

import androidx.lifecycle.ViewModel
import com.libertytech.tinderlike.usecases.RegisterUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUIState())
    private val registerUseCase: RegisterUseCase = RegisterUseCase()
    val uiState: StateFlow<RegisterUIState> = _uiState.asStateFlow()


    fun register(mail: String, password: String){
        CoroutineScope(Dispatchers.IO).launch {
            _uiState.value = RegisterUIState(registerUseCase.execute(mail, password))
        }
    }


}
data class RegisterUIState(val init: Boolean = false)