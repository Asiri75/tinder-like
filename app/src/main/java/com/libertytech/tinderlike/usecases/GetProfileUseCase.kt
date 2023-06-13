import com.libertytech.tinderlike.model.User
import com.libertytech.tinderlike.repositories.UserRepository

class GetProfileUseCase {
    private val userRepository : UserRepository = UserRepository()

    suspend fun execute(id: Int): User {
        return userRepository.getProfile(id)
    }
}