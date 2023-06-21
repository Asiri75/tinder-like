package com.libertytech.tinderlike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.libertytech.tinderlike.screens.login.LoginScreen
import com.libertytech.tinderlike.screens.profile.ProfileScreen
import com.libertytech.tinderlike.screens.register.RegisterScreen
import com.libertytech.tinderlike.ui.theme.TinderLikeTheme
import com.libertytech.tinderlike.usecases.UserIsAuthUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userIsAuthUseCase = UserIsAuthUseCase()
        val startDestination = if (userIsAuthUseCase.execute())
            "profile"
        else "login"

        setContent {
            TinderLikeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainScreenView(startDestination = startDestination)
                }
            }
        }
    }
}

@Composable
fun MainScreenView(
    navController: NavHostController = rememberNavController(),
     startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                navigateToRegister = { navController.navigate("register") },
                navigateToProfile = { navController.navigate("profile") }
            )
        }
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = { navController.navigate("login") },
                onNavigateToProfile = { navController.navigate("profile") }
            )
        }
        composable("profile") { ProfileScreen() }
    }
}