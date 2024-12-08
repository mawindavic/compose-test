package com.mawinda.composetest.ui.screens

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel()) {

    val selectedMed = viewModel.selectedMed.collectAsState()

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(text = selectedMed.value?.name?.replaceFirstChar { it.uppercase() } ?: "Detail")
        },
        navigationIcon = {

        }
    )
}