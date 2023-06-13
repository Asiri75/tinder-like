package com.libertytech.tinderlike

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.libertytech.tinderlike.model.Partenaire
import com.libertytech.tinderlike.repositories.UserRepository
import com.libertytech.tinderlike.ui.theme.TinderLikeTheme
import com.libertytech.tinderlike.usecases.GetPartenaires
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {
    private val userRepository = UserRepository()
    private val getPartenaires = GetPartenaires(userRepository)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TinderLikeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    FetchPartenaires()
                }
            }
        }
    }




@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FetchPartenaires() {
    val partenaires = mutableListOf<Partenaire>()

    GlobalScope.launch(Dispatchers.IO) {
        partenaires.addAll(getPartenaires())
    }

    // Affiche la liste des partenaires récupérés depuis Firebase
    for (partenaire in partenaires) {
        Text(text = "Nom: ${partenaire.nom}, Age: ${partenaire.age}, Description: ${partenaire.description}")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TinderLikeTheme {
        FetchPartenaires()
    }
}
}

