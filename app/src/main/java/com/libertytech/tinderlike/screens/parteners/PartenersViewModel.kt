package com.libertytech.tinderlike.screens.parteners

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.libertytech.tinderlike.model.User
import com.libertytech.tinderlike.repositories.UserRepository
import com.libertytech.tinderlike.usecases.GetPartenersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PartenairesViewModel(
    private val getPartenairesUseCase: GetPartenersUseCase
) : ViewModel() {

    private val userRepository = UserRepository()

    private val _partenairesState = MutableStateFlow<List<User>>(emptyList())
    val partenairesState: StateFlow<List<User>> = _partenairesState

    init {
        fetchPartenaires()
    }

     fun fetchPartenaires() {
        viewModelScope.launch {
            val result = getPartenairesUseCase.invoke()
            _partenairesState.value = result
        }
    }
}
