package com.libertytech.tinderlike.repositories

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.libertytech.tinderlike.model.User
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val db = Firebase.firestore.collection("users")

    suspend fun getProfile(id: String) {
        try {
            val document = db.document(id).get().await()

            if (document.exists()) {
                Log.d("UserRepository - getProfile", "DocumentSnapshot data: ${document.data}")
            } else {
                Log.d("UserRepository - getProfile", "No such document")
            }
        } catch (exception: Exception) {
            Log.d("UserRepository - getProfile", "get failed with ", exception)
        }
    }

    suspend fun getPartenaires(): List<User> {
        val partenaires = listOf<User>()

        try {
            val querySnapshot = db.get().await()

           var partenaires = querySnapshot.documents.mapNotNull { it.toObject() }



        } catch (exception: Exception) {
            Log.d("UserRepository - getPartenaires", "Error getting partenaires: ", exception)
        }

        return partenaires
    }
}
