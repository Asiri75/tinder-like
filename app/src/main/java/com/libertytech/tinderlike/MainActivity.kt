package com.libertytech.tinderlike

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.libertytech.tinderlike.ui.theme.TinderLikeTheme
import com.libertytech.tinderlike.usecases.Register
import com.libertytech.tinderlike.usecases.UserIsAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TinderLikeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }

        val register = Register()
        val userIsAuth = UserIsAuth()
        CoroutineScope(Dispatchers.IO).launch {
            val response = register.execute("lukas.descoins.ld@gmail.com", "secret")
            if (userIsAuth.execute()) {
                print("user is log")
            } else {
                print("user is not log")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TinderLikeTheme {
        Greeting("Android")
    }
}