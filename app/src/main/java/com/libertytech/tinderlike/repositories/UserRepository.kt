package com.libertytech.tinderlike.repositories

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.libertytech.tinderlike.model.User
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val db = Firebase.firestore.collection("users")

    suspend fun getProfile(id: String): User? {
        return try {
            val document = db.document(id).get().await()
            val user = if (document.exists()) {
                Log.d("UserRepository - getProfile", "DocumentSnapshot data: ${document.data}")
                document.toObject(User::class.java)
            } else {
                Log.d("UserRepository - getProfile", "No such document")
                null
            }
            user
        } catch (exception: Exception) {
            Log.d("UserRepository - getProfile", "get failed with ", exception)
            null
        }
    }

    suspend fun getPartners(): List<User> {
        var partners = listOf<User>()

        try {
            val querySnapshot = db.get().await()


            partners = querySnapshot.documents.mapNotNull { it.toObject(User::class.java) }
        } catch (exception: Exception) {
            Log.d("UserRepository - getPartenaires", "Error getting partenaires: ", exception)
        }

        return partners
    }

    fun updateProfile(user: User) {
        val docRef = db.document(user.id.orEmpty())

        docRef.set(user)
            .addOnSuccessListener {
                Log.d("UserRepository - updateProfile", "User updated successfully")
            }
            .addOnFailureListener { exception ->
                Log.d("UserRepository - updateProfile", "Failed to update user", exception)
            }
    }

    suspend fun addUser(pictureUrl: String, name: String, description: String) {
        val user = hashMapOf(
            "pictureUrl" to pictureUrl,
            "name" to name,
            "description" to description
        )

        firestore.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                // L'ajout a réussi, vous pouvez obtenir l'ID du document ajouté avec documentReference.id
            }
            .addOnFailureListener { e ->
                // Une erreur s'est produite lors de l'ajout des données
            }
    }
}
