package com.libertytech.tinderlike.repositories

import com.libertytech.tinderlike.model.User
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserRepository {
    fun getProfile(id: String) {
        val db = Firebase.firestore
        val docRef = db.collection("cities").document("SF")
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