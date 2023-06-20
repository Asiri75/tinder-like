package com.libertytech.tinderlike.screens.register

import Email
import Password
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.libertytech.tinderlike.screens.login.LoginViewModel

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = viewModel(),
    onNavigateToLogin: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    RegisterLayout(
        registerViewModel = RegisterViewModel(),
        onNavigateToLogin = onNavigateToLogin,
        navigateToProfile = onNavigateToProfile)
}

@Composable
fun RegisterLayout(
    registerViewModel: RegisterViewModel,
    onNavigateToLogin: () -> Unit,
    navigateToProfile: () -> Unit
) {
    val userEmail = remember { mutableStateOf(TextFieldValue("toto@toto.fr")) }
    val userPassword = remember { mutableStateOf(TextFieldValue("totodhefr")) }
    val registerUiState = registerViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        val focusManager = LocalFocusManager.current

        if (registerUiState.value.init) {
            navigateToProfile()
        }
        Text(
            text = "Teender",
            style = MaterialTheme.typography.h1,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Sign up",
            style = MaterialTheme.typography.subtitle1,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Email(userEmail)
        Password(userPassword)

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { registerViewModel.register(userEmail.value.text, userPassword.value.text) }
        ) {
            Text(
                text = "Sign Up",
                fontSize = 16.sp
            )
        }

        Text(
            text = "Already have an account? Login",
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .clickable(
                    onClick = onNavigateToLogin,
                )
                .padding(top = 16.dp),
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold
        )
    }
}

