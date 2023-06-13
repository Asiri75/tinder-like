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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.libertytech.tinderlike.model.Partenaire
import com.libertytech.tinderlike.ui.theme.TinderLikeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val partenairesCollection = db.collection("user")

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
            try {
                val querySnapshot: QuerySnapshot = partenairesCollection.get().await()
                val documents = querySnapshot.documents

                for (document in documents) {
                    val nom = document.getString("nom") ?: ""
                    val age = document.getLong("age")?.toInt() ?: 0
                    val description = document.getString("description") ?: ""
                    val pictureUrl = document.getString("pictureUrl") ?: ""

                    val partenaire = Partenaire(nom, age, description, pictureUrl)
                    partenaires.add(partenaire)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
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
