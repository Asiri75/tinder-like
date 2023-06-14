package com.libertytech.tinderlike.repositories

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.libertytech.tinderlike.model.User
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val db = Firebase.firestore.collection("users")

    suspend fun getProfile(id: String): User? {
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
        return null
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

    suspend fun updateProfile(user: User) {
        val docRef = db.document(user.id)

        docRef.set(user)
            .addOnSuccessListener {
                Log.d("UserRepository - updateProfile", "User updated successfully")
            }
            .addOnFailureListener { exception ->
                Log.d("UserRepository - updateProfile", "Failed to update user", exception)
            }
    }
}
