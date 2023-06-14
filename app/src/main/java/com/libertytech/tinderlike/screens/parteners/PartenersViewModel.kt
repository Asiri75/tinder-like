package com.libertytech.tinderlike.screens.parteners

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.libertytech.tinderlike.model.User
import com.libertytech.tinderlike.repositories.UserRepository
import com.libertytech.tinderlike.usecases.GetPartenersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PartnersViewModel: ViewModel() {
    private val getPartenersUseCase = GetPartenersUseCase()
    private val userRepository = UserRepository()

    private val _partnersState = MutableStateFlow<List<User>>(emptyList())
    val partnersState: StateFlow<List<User>> = _partnersState



     fun fetchPartenaires() {
        viewModelScope.launch {
            val result = getPartenersUseCase.invoke()
            _partnersState.value = result
        }
    }
}
