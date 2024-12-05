package com.mawinda.composetest.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    BackHandler {
        onBackPressed()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(text = "Detail Content")
    }
}
