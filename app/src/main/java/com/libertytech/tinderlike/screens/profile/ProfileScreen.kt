package com.libertytech.tinderlike.screens.profile
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

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
        val searchedName = remember { mutableStateOf(TextFieldValue("")) }
        val searchedDescription = remember { mutableStateOf(TextFieldValue("")) }
        val searchedImageUrl = remember { mutableStateOf(TextFieldValue("")) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = searchedName.value,
                onValueChange = { searchedName.value = it },
            )
            TextField(
                value = searchedDescription.value,
                onValueChange = { searchedDescription.value = it },
            )
            TextField(
                value = searchedImageUrl.value,
                onValueChange = { searchedImageUrl.value = it },
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { brandNameViewModel.makeRequest(searchedBrandName.value.text) }
            ) {
                Text(
                    text = "C'est parti !",
                    fontSize = 15.sp
                )
            }
        }
    }
