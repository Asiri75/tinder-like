package com.libertytech.tinderlike.screens.login

import Email
import Password
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    navigateToRegister: () -> Unit,
    navigateToProfile: () -> Unit
) {
    val loginUiState by loginViewModel.uiState.collectAsState()
    LoginLayout(loginViewModel = loginViewModel, loginUiState = loginUiState, navigateToRegister = navigateToRegister, navigateToProfile = navigateToProfile)
}

@Composable
fun LoginLayout(
    loginViewModel: LoginViewModel,
    loginUiState: LoginUIState,
    navigateToRegister: () -> Unit,
    navigateToProfile: () -> Unit

) {
    val userEmail = remember { mutableStateOf(TextFieldValue("")) }
    val userPassword = remember { mutableStateOf(TextFieldValue("")) }

    if (loginUiState.userIsLog) {
        navigateToProfile()
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        val focusManager = LocalFocusManager.current

        Text(
            text = "Teender",
            style = typography.h1,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Login",
            style = typography.subtitle1,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Email(userEmail)
        Password(userPassword)

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { loginViewModel.login(userEmail.value.text, userPassword.value.text) }
        ) {
            Text(
                text = "Login",
                fontSize = 16.sp
            )
        }

        Text(
            text = "Don't have an account? Register",
            style = typography.body2,
            modifier = Modifier.clickable(
                onClick = navigateToRegister,
            ).padding(top = 16.dp),
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold
        )
    }
}