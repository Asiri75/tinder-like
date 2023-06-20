package com.libertytech.tinderlike.usecases

import com.libertytech.tinderlike.repositories.AuthRepository

class UserIsAuthUseCase {

    private val authRepository: AuthRepository = AuthRepository()

    fun execute(): Boolean {
        return try {
            authRepository.userIsAuth() != null
        } catch (e: Exception){
            e.printStackTrace()
            false
        }
    }
}