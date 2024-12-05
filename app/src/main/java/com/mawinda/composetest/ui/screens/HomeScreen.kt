package com.mawinda.composetest.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel()) {
    Text(text = "Home Screen")
    // TODO: Implement HomeScreen
}