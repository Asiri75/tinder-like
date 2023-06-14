package com.libertytech.tinderlike.screens.profile

import GetProfileUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.libertytech.tinderlike.model.User
import com.libertytech.tinderlike.repositories.AuthRepository
import com.libertytech.tinderlike.repositories.UserRepository
import com.libertytech.tinderlike.usecases.UpdateProfileUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


data class ProfileUiState(
    val name: String = "Nom",
    val description: String = "Description !",
    val pictureUrl: String = "PictureUrl"
)

class ProfileViewModel : ViewModel() {
    private val getProfileUseCase = GetProfileUseCase()
    private val updateProfileUseCase = UpdateProfileUseCase()

    private val _uiState = MutableStateFlow(
        User(
            id = "3",
            pictureUrl = "a",
            name = "name",
            description = "description"
        )
    )
    val uiState: StateFlow<User> = _uiState.asStateFlow()

    fun makeRequest(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = updateProfileUseCase.execute(user)

            withContext(Dispatchers.Main) {
                if (response != null) {
//                    _uiState.value = ProfileUiState("Nom","Description","pictureUrl")
//                }
                }
            }
        }

        fun getProfile() {
            viewModelScope.launch {
                val response = getProfileUseCase.execute()

                withContext(Dispatchers.Main) {
                    if (response != null) {
                        _uiState.value = response
                    }
                }
            }
        }
    }
}
