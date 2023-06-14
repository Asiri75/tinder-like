package com.libertytech.tinderlike.screens.profile

import GetProfileUseCase
import androidx.lifecycle.ViewModel
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
class ProfileViewModel: ViewModel() {
    private val getProfileUseCase = GetProfileUseCase()
    private val updateProfileUseCase = UpdateProfileUseCase()

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        var user = ProfileUiState("Nom","Description","pictureUrl")
        _uiState.value = user
    }
    fun makeRequest(user: com.libertytech.tinderlike.model.User) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = updateProfileUseCase.execute(user)

            withContext(Dispatchers.Main) {
                if (response != null) {
                    _uiState.value = ProfileUiState("Nom","Description","pictureUrl")
                }
            }
        }
    }
}