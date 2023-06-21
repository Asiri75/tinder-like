package com.libertytech.tinderlike.usecases

import com.libertytech.tinderlike.model.User
import com.libertytech.tinderlike.repositories.AuthRepository
import com.libertytech.tinderlike.repositories.UserRepository

class UpdateProfileUseCase {

    private val userRepository : UserRepository = UserRepository()
    private val authRepository: AuthRepository = AuthRepository()

    suspend fun execute(user: User) {
        return userRepository.updateProfile(user.copy(id = authRepository.getUserId()))
    }

}