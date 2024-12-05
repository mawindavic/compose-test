package com.mawinda.composetest.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mawinda.composetest.ui.model.Screen

@Composable
fun NavigationScreen(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()
) {

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.LOGIN.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.LOGIN.name) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Screen.HOME.name)
                    }
                )
            }

            composable(Screen.HOME.name) {
                HomeScreen(onNavigateToDetail = {
                    navController.navigate(Screen.DETAIL.name)
                }, onLogout = {
                    navController.popBackStack(Screen.LOGIN.name, inclusive = false)
                })
            }

            composable(Screen.DETAIL.name) {
                DetailScreen(onBackPressed = {
                    navController.popBackStack()
                })
            }
        }
    }


}