package com.libertytech.tinderlike.usecases

import com.libertytech.tinderlike.repositories.AuthRepository

class GetUserIdUseCase {
    private val authRepository: AuthRepository = AuthRepository()

    suspend fun execute(): String? {
        if (authRepository.userIsAuth() != null) {
            return authRepository.getUserId()
        }

        return null
    }
}