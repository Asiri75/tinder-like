package com.libertytech.tinderlike.usecases

import com.libertytech.tinderlike.repositories.AuthRepository

class Register {
    private val authRepository: AuthRepository = AuthRepository()

    suspend fun execute(email: String, password: String) {
        return authRepository.register(email, password)
    }
}