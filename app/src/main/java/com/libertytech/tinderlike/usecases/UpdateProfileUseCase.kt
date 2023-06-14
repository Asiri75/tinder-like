package com.libertytech.tinderlike.usecases

import com.libertytech.tinderlike.model.User
import com.libertytech.tinderlike.repositories.UserRepository

class UpdateProfileUseCase {

    private val userRepository : UserRepository = UserRepository()

    suspend fun execute(id: Int) {
        val user = User(
            id = "123456",
            pictureUrl = "https://example.com/picture.jpg",
            name = "John Doe",
            description = "Lorem ipsum dolor sit amet."
        )
        return userRepository.updateProfile(user)
    }

}