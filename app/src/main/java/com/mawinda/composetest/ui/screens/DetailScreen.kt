package com.mawinda.composetest.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel()) {
    Text(text = "Detail Screen")
    //TODO: Implement detail screen
}