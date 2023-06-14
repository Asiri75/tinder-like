import com.libertytech.tinderlike.model.User
import com.libertytech.tinderlike.repositories.AuthRepository
import com.libertytech.tinderlike.repositories.UserRepository

class GetProfileUseCase {
    private val userRepository: UserRepository = UserRepository()
    private val authRepository: AuthRepository = AuthRepository()

    suspend fun execute(): User? {
        val user_id = authRepository.getUserId()
        if (user_id != null) {
            return userRepository.getProfile(user_id)
        }
        return null
    }
}