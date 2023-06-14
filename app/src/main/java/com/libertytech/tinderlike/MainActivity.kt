package com.libertytech.tinderlike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.libertytech.tinderlike.model.Partenaire
import com.libertytech.tinderlike.screens.login.LoginScreen
import com.libertytech.tinderlike.ui.theme.TinderLikeTheme
import com.libertytech.tinderlike.usecases.UserIsAuthUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class LoginUiState(
    val userIsAuth: Boolean = false,
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crée une liste de partenaires
        val listePartenaires = mutableListOf(
            Partenaire("Alice", 25, "J'aime les voyages et les chiens.", "https://static-cse.canva.com/blob/605714/canva_photo_profil_reseaux_sociaux.jpg"),
            Partenaire("Bob", 30, "Je suis passionné de photographie.", "https://www.jeancoutu.com/globalassets/revamp/photo/conseils-photo/20160302-01-reseaux-sociaux-profil/photo-profil_301783868.jpg"),
            Partenaire("Caroline", 28, "J'adore la musique et la danse.", "https://blog.neocamino.com/wp-content/uploads/2014/06/photo-profil.jpg")
        )

        // Affiche les noms des partenaires dans la console
        for (partenaire in listePartenaires) {
            println(partenaire.nom)
        }

        //Firebase.auth.signOut()
        val _uiState = MutableStateFlow(LoginUiState())
        val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

        CoroutineScope(Dispatchers.IO).launch {
            val userIsAuth = UserIsAuthUseCase().execute()
            withContext(Dispatchers.Main) {
                _uiState.value = LoginUiState(userIsAuth)
            }
        }
        setContent {
            TinderLikeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    if (uiState.collectAsState().value.userIsAuth) {
                        Greeting(name = "World")
                    } else {
                        LoginScreen()
                    }
                }
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