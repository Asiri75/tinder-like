package com.libertytech.tinderlike.usecases

import com.libertytech.tinderlike.model.User
import com.libertytech.tinderlike.repositories.UserRepository

class UpdateProfileUseCase {

    private val userRepository : UserRepository = UserRepository()

    suspend fun execute(user: User) {
        return userRepository.updateProfile(user)
    }

}