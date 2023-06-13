package com.libertytech.tinderlike.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepository {
    private var auth: FirebaseAuth = Firebase.auth
    suspend fun callFirebaseIsAuth (): FirebaseUser? {
        return try {
            auth.currentUser
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }


    suspend fun Register(email: String, password: String): FirebaseUser? {
        auth.createUserWithEmailAndPassword(email, password)
        return auth.currentUser
    }

    suspend fun Login(email: String, password: String): FirebaseUser? {
        auth.signInWithEmailAndPassword(email, password)
        return auth.currentUser
    }
}