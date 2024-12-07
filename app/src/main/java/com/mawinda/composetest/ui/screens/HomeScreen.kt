package com.mawinda.composetest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mawinda.composetest.R
import com.mawinda.domain.model.Med
import com.mawinda.domain.model.UiState
import timber.log.Timber
import java.util.Locale

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    onLogout: () -> Unit,
    onNavigateToDetail: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = viewModel.username.collectAsState()
        val greetings = viewModel.greetings.collectAsState()
        ListItem(
            headlineContent = {
                Text(
                    text = greetings.value, style = MaterialTheme.typography.bodyLarge
                )
            },
            supportingContent = {
                Text(
                    text = username.value,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            },
            trailingContent = {
                Image(painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            onLogout()
                        })
            },
        )
        val uiState = viewModel.uiState.collectAsState()

        LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
            item {
                Text(
                    text = "Meds",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            when (val state = uiState.value) {
                is UiState.Error -> {
                    item {
                        Text(text = state.message)
                    }
                }

                UiState.Idle -> Timber.w("Idle")
                UiState.Loading -> item {
                    Text(text = "Loading")
                }

                is UiState.Success<*> -> {
                    if (state.data is List<*>) {
                        val list = state.data as List<*>

                        if (list.isEmpty()) {
                            item {
                                Text(text = "No data")
                            }
                        } else {
                            val meds = list.filterIsInstance<Med>()
                            items(meds) { med ->
                                Card(onClick = {
                                    viewModel.selectMed(med).also {
                                        onNavigateToDetail()
                                    }
                                }) {
                                    ListItem(headlineContent = {
                                        Text(
                                            text = med.name.replaceFirstChar {
                                                if (it.isLowerCase()) it.titlecase(
                                                    Locale.getDefault()
                                                ) else it.toString()
                                            },
                                            style = MaterialTheme.typography.bodyLarge,
                                            modifier = Modifier.padding(bottom = 4.dp)
                                        )
                                    }, supportingContent = {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(4.dp)
                                        ) {
                                            Text(
                                                text = "Dose: ${med.dose}",
                                                style = MaterialTheme.typography.bodySmall,
                                            )
                                            Text(
                                                text = "Strength: ${med.strength}",
                                                style = MaterialTheme.typography.bodySmall,
                                            )
                                        }
                                    })
                                }

                            }
                        }

                    }

                }
            }


        }

    }


}