package com.libertytech.tinderlike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.libertytech.tinderlike.model.Partenaire
import com.libertytech.tinderlike.screens.profile.ProfileScreen
import com.libertytech.tinderlike.ui.theme.TinderLikeTheme
import com.libertytech.tinderlike.usecases.GetPartenersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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
                    ProfileScreen()
                }
            }
        }
    }





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TinderLikeTheme {

    }
}
}

