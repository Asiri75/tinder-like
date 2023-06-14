package com.libertytech.tinderlike.repositories

import com.libertytech.tinderlike.model.User
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.coroutines.tasks.await

class UserRepository {
    private val db = Firebase.firestore.collection("users")

    suspend fun getProfile(id: String) {
        try {
            val document = db.document(id).get().await()

            if (document.exists()) {
                Log.d("UserRepository- getProfile", "DocumentSnapshot data: ${document.data}")

                return document.toObject(User::class.java)
            } else {
                Log.d("UserRepository - getProfile", "No such document")
            }
        } catch (exception: Exception) {
            Log.d("UserRepository - getProfile", "get failed with ", exception)
        }
        return null;
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