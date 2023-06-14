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
import com.libertytech.tinderlike.screens.register.RegisterScreen
import com.libertytech.tinderlike.ui.theme.TinderLikeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TinderLikeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainScreenView()
                }
            }
        }
    }
}

@Composable
fun MainScreenView(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "login"
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") { LoginScreen(onNavigateToRegister = { navController.navigate("register") }) }
        composable("register") { RegisterScreen(onNavigateToLogin = { navController.navigate("login") }) }
    }
}