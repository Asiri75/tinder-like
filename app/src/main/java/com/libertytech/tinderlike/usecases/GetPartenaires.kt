package com.libertytech.tinderlike.usecases

import com.libertytech.tinderlike.model.Partenaire
import com.libertytech.tinderlike.repositories.UserRepository

class GetPartenaires(private val userRepository: UserRepository) {
    suspend operator fun invoke(): List<Partenaire> {
        val partenaires = mutableListOf<Partenaire>()

        return partenaires
    }
}
