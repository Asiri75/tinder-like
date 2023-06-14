package com.libertytech.tinderlike.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private var auth: FirebaseAuth = Firebase.auth

    fun userIsAuth (): FirebaseUser? {
        return try {
            auth.currentUser
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getUserId(): String? {
        return auth.currentUser?.uid
    }

    suspend fun register(email: String, password: String): Boolean {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await().user != null
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun login(email: String, password: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, password).await().user != null
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun logout(): Boolean{
        return try {
            auth.signOut()
            true
        }catch(e: Exception){
            e.printStackTrace()
            false
        }
    }
}