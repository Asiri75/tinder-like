package com.libertytech.tinderlike.usecases

import com.libertytech.tinderlike.repositories.AuthRepository

class LogoutUseCase {
    private val authRepository: AuthRepository = AuthRepository()

    suspend fun execute(): Boolean {
        return try {
            authRepository.logout()
            true
        } catch (e: Exception){
            e.printStackTrace()
            false
        }
    }
}