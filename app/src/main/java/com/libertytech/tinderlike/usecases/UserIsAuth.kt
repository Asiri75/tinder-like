package com.libertytech.tinderlike.usecases

import com.libertytech.tinderlike.repositories.AuthRepository

class UserIsAuth {

    private val authRepository: AuthRepository = AuthRepository()
    private var isAuth: Boolean = false
    suspend fun execute(): Boolean {
        try {
            val user = authRepository.callFirebaseIsAuth()
            if(user != null){
                isAuth = true
                return isAuth
            }else{
                isAuth = false
                return isAuth
            }
        }catch (e: Exception){
            e.printStackTrace()
            isAuth = false
            return isAuth
        }
    }
}