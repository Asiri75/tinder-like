package com.libertytech.tinderlike.repositories

import com.libertytech.tinderlike.model.User
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserRepository {
    private val db = Firebase.firestore.collection("users")
    fun getProfile(id: String) {
       val docRef = db.document(id)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("UserRepository - getProfile", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("UserRepository - getProfile", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("UserRepository - getProfile", "get failed with ", exception)
            }
    }
}