package com.libertytech.tinderlike.usecases

import com.libertytech.tinderlike.repositories.AuthRepository

class LoginUseCase {
    private val authRepository: AuthRepository = AuthRepository()

    suspend fun execute(email: String, password: String): Boolean {
        return authRepository.login(email, password)
    }
}