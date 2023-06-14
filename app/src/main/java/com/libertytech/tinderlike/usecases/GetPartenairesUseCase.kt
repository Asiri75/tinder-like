package com.libertytech.tinderlike.usecases

class GetPartenairesUseCase() {
    suspend operator fun invoke(): List<Partenaire> {
        val partenaires = mutableListOf<Partenaire>()

        return partenaires
    }
}
