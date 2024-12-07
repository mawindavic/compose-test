package com.mawinda.composetest.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    onBackPressed: () -> Unit
) {
    val selectedMed = viewModel.selectedMed.collectAsState()

    BackHandler {
        onBackPressed()
    }
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        selectedMed.value?.let {
            Text(text = it.name)
        }
    }

}
