import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun Email(userEmail: MutableState<TextFieldValue>) {
    OutlinedTextField(
        value = userEmail.value,
        onValueChange = { userEmail.value = it },
        label = { Text(text = "Email") },
        placeholder = { Text(text = "Enter your email") }
    )
}

@Composable
fun Password(userPassword: MutableState<TextFieldValue>) {
    var showPassword by remember {
        mutableStateOf(false)
    }

    TextField(
        value = userPassword.value,
        onValueChange = { userPassword.value = it },
        label = { Text(text = "Password") },
        placeholder = { Text(text = "Enter your password") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Lock Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    imageVector = if (showPassword) Icons.Outlined.Clear  else Icons.Outlined.Call,
                    contentDescription = if (showPassword) "Show Password" else "Hide Password"
                )
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
}