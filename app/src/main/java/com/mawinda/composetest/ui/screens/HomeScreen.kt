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
import androidx.hilt.navigation.compose.hiltViewModel
import com.mawinda.composetest.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
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

        LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
            item {
                Text(
                    text = "Details",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            items(32) {
                Card(onClick = {
                    onNavigateToDetail()
                }) {
                    ListItem(headlineContent = {
                        Text(
                            text = "Item $it",
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
                                text = "This is item $it",
                                style = MaterialTheme.typography.bodySmall,
                            )
                            Text(
                                text = "This is item $it",
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }
                    })
                }
            }


        }

    }


}