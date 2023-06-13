package com.libertytech.tinderlike.usecases

import com.libertytech.tinderlike.repositories.AuthRepository

class UserIsAuth {

    private val authRepository: AuthRepository = AuthRepository()

    suspend fun execute(): Boolean {
        return try {
            authRepository.userIsAuth() != null
        } catch (e: Exception){
            e.printStackTrace()
            false
        }
    }
}