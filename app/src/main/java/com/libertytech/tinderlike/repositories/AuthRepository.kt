package com.libertytech.tinderlike.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepository {
    private var auth: FirebaseAuth = Firebase.auth

    suspend fun userIsAuth (): FirebaseUser? {
        return try {
            auth.currentUser
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun register(email: String, password: String): Boolean {
        return try {
            auth.createUserWithEmailAndPassword(email, password).isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun login(email: String, password: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, password).isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}