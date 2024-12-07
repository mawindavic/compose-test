package com.mawinda.composetest.ui.screens

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel()) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(text = "Detail Screen")
        },
        navigationIcon = {

        }
    )
}