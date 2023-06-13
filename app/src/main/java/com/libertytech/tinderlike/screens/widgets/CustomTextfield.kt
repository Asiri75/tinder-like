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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun Email() {
    var value by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        label = { Text(text = "Email") },
        placeholder = { Text(text = "Enter your email") }
    )
}

@Composable
fun Password() {
    var value by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    TextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
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