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

    private val _likesState = MutableStateFlow<List<Pair<String, String>>>(emptyList())
    val likesState: StateFlow<List<Pair<String, String>>> = _likesState



     fun fetchPartenaires() {
        viewModelScope.launch {
            val result = getPartenersUseCase.invoke()
            _partnersState.value = result
        }
    }
    fun likePartenaire(partenaire: User, userId: String) {
        // Logique pour ajouter le partenaire et l'ID de l'utilisateur à la liste des likes
        val pair = userId to partenaire.id
        _likesState.value = _likesState.value + pair
    }

    fun dislikePartenaire(partenaire: User) {

        _partnersState.value = _partnersState.value.filter { it != partenaire }
    }
}
