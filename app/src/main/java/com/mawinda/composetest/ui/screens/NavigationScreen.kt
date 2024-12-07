package com.mawinda.composetest.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mawinda.domain.model.Screen

@Composable
fun NavigationScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = hiltViewModel()
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    Scaffold(topBar = {
        if (currentBackStack?.destination?.route == Screen.DETAIL.name) {
            AppBar()
        }
    }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.LOGIN.name,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            composable(Screen.LOGIN.name) {
                LoginScreen(onLoginSuccess = {
                    navController.navigate(Screen.HOME.name)
                })
            }

            composable(Screen.HOME.name) {

                HomeScreen(viewModel = viewModel, onNavigateToDetail = {
                    navController.navigate(Screen.DETAIL.name)
                }, onLogout = {
                    navController.popBackStack(Screen.LOGIN.name, inclusive = false)
                })
            }

            composable(Screen.DETAIL.name) {
                DetailScreen(viewModel = viewModel, onBackPressed = {
                    navController.popBackStack()
                })
            }
        }
    }

}