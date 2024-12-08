package com.mawinda.composetest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

        Greetings(viewModel, onLogout)
        Dashboard(viewModel, onNavigateToDetail)

    }
}

@Composable
private fun Greetings(
    viewModel: MainViewModel, onLogout: () -> Unit, modifier: Modifier = Modifier
) {
    val username = viewModel.username.collectAsState()
    val greetings = viewModel.greetings.collectAsState()

    ListItem(
        modifier = modifier.padding(top = 20.dp),
        headlineContent = {
            Text(
                text = greetings.value, style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                minLines = 1,
                modifier = Modifier.padding(top = 8.dp)
            )
        },
        supportingContent = {
            Text(
                text = username.value,
                style = MaterialTheme.typography.displaySmall,
                maxLines = 1,
                minLines = 1,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 4.dp)
            )
        },
        trailingContent = {
            Image(painter = painterResource(R.drawable.person),
                contentDescription = stringResource(R.string.account_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .border(1.dp, color = Color.Gray, shape = CircleShape)
                    .background(color = Color.Gray, shape = CircleShape)
                    .clickable {
                        onLogout()
                    })
        },
    )
}

@Composable
private fun Dashboard(
    viewModel: MainViewModel, onNavigateToDetail: () -> Unit, modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState()
    LazyColumn(modifier = modifier.padding(top = 16.dp)) {
        item {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
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
                        emptyMedList()
                    } else {
                        val meds = list.filterIsInstance<Med>()
                        items(meds) { med ->
                            MedItemCard(med) {
                                viewModel.selectMed(med).also {
                                    onNavigateToDetail()
                                }
                            }

                        }
                    }

                }

            }
        }


    }
}

@Composable
private fun MedItemCard(
    med: Med,
    modifier: Modifier = Modifier,
    onSelected: () -> Unit,

    ) {
    Card(modifier = modifier.padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.elevatedCardColors(),
        onClick = {
            onSelected()
        }) {
        ListItem(headlineContent = {
            Text(
                text = med.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }, supportingContent = {
            Text(
                text = "Dose: ${med.dose.ifEmpty { "N/A" }}",
                style = MaterialTheme.typography.bodySmall,
            )
        }, trailingContent = {
            Text(
                text = med.strength,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        })
    }
}

private fun LazyListScope.emptyMedList() {
    item {
        Text(text = "No data")
    }
}