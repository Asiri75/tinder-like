package com.libertytech.tinderlike.screens.profile

import GetProfileUseCase
import androidx.lifecycle.ViewModel
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

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun makeRequest(brandDescription: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getProfileUseCase.execute("user")

            withContext(Dispatchers.Main) {
                if (response != null) {
                    _uiState.value = ProfileUiState(
                        name = response.name,
                        description = response.description,
                        pictureUrl = response.pictureUrl
                    )
                }
            }
        }
    }
}