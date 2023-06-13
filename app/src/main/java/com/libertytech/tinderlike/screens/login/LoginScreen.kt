package com.libertytech.tinderlike.screens.login

import Email
import Password
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertytech.tinderlike.R

@Composable
fun LoginScreen() {
    LoginLayout()
}

@Composable
fun LoginLayout() {
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
            text = "Enter Your Username & Password",
            style = typography.subtitle1,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Email()
        Password()

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { }
        ) {
            Text(
                text = "Login",
                fontSize = 16.sp
            )
        }
    }
}