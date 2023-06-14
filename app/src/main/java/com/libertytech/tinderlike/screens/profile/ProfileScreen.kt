package com.libertytech.tinderlike.screens.profile
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.libertytech.tinderlike.model.User

@Composable
    fun ProfileScreen(
        profileViewModel: ProfileViewModel = viewModel()
    ) {
        val profileUiState by profileViewModel.uiState.collectAsState()
        ProfileLayout(profileViewModel = profileViewModel, profileUiState = profileUiState)
    }
    @Composable
    fun ProfileLayout(
        profileViewModel: ProfileViewModel,
        profileUiState: ProfileUiState,
    ){
        val user = remember { mutableStateOf(User("", "", "", "")) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = user.value.name,
                onValueChange = { user.value = user.value.copy(name = it) },
            )

            TextField(
                value = user.value.description,
                onValueChange = { user.value = user.value.copy(description = it) },
            )

            TextField(
                value = user.value.pictureUrl,
                onValueChange = { user.value = user.value.copy(pictureUrl = it) },
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { profileViewModel.makeRequest(user.value) }
            ) {
                Text(
                    text = "C'est parti !",
                    fontSize = 15.sp
                )
            }
        }
    }
