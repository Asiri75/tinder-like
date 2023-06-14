package com.libertytech.tinderlike.screens.profile
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.libertytech.tinderlike.model.User

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel()
) {
    val profileUiState by profileViewModel.uiState.collectAsState()
    val user = remember { mutableStateOf(profileUiState) }
    ProfileLayout(profileViewModel = profileViewModel, profileUiState = profileUiState, user = user)
}

@Composable
fun ProfileLayout(
    profileViewModel: ProfileViewModel,
    profileUiState: User,
    user: MutableState<User>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Modifier le profil",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextFieldWithLabel(
            label = "Nom",
            value = user.value.name,
            onValueChange = { user.value = user.value.copy(name = it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextFieldWithLabel(
            label = "Description",
            value = user.value.description,
            onValueChange = { user.value = user.value.copy(description = it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextFieldWithLabel(
            label = "URL de l'image",
            value = user.value.pictureUrl,
            onValueChange = { user.value = user.value.copy(pictureUrl = it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { profileViewModel.makeRequest(user.value) }
        ) {
            Text(
                text = "Mettre à jour",
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun TextFieldWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(text = label)
        TextField(value = value, onValueChange = onValueChange, modifier = Modifier.fillMaxWidth())
    }
}