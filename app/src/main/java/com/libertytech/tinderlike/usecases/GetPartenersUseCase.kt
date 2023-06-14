package com.libertytech.tinderlike.usecases
import com.libertytech.tinderlike.model.User
import com.libertytech.tinderlike.repositories.UserRepository
class GetPartenersUseCase() {
    private val userRepository : UserRepository = UserRepository()

    suspend operator fun invoke(): List<User> {
        return userRepository.getPartenaires()
    }
}
