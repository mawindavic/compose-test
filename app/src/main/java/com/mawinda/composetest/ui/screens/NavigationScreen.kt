package com.mawinda.composetest.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class Screen {
    LOGIN, HOME, DETAIL
}

@Composable
fun NavigationScreen(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()
) {
    Box(modifier = modifier) {
        NavHost(navController = navController, startDestination = Screen.LOGIN.name) {
            composable(Screen.LOGIN.name) {
                LoginScreen()
            }

            composable(Screen.HOME.name) {
                HomeScreen()
            }

            composable(Screen.DETAIL.name) {
                DetailScreen()
            }
        }
    }
}