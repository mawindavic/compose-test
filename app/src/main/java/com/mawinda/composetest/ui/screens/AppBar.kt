package com.mawinda.composetest.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mawinda.composetest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    canNavigateBack: Boolean = false,
    showLogoutButton: Boolean = false,
    showTitle: Boolean = false,
    onBackPressed: () -> Unit = {}
) {

    var showLogoutDialog by remember {
        mutableStateOf(false)
    }

    val selectedMed = viewModel.selectedMed.collectAsState()
    TopAppBar(
        modifier = modifier.padding(horizontal = 16.dp),
        title = {
            if (showTitle) {
                Text(text = selectedMed.value?.name?.replaceFirstChar { it.uppercase() } ?: "")
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(
                            id = R.string.navigate_up
                        )
                    )
                }
            }
        },
        actions = {
            if (showLogoutButton) {
                IconButton(onClick = {
                    showLogoutDialog = true

                }, modifier = Modifier.size(24.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.logout),
                        contentDescription = stringResource(
                            id = R.string.logout
                        )
                    )
                }
            }
        }
    )

    if (showLogoutDialog) {
        ConfirmationDialog(message = "You want to logout?", onDismiss = {
            showLogoutDialog = false
        }, onConfirm = {
            showLogoutDialog = false
            viewModel.logout()
            onBackPressed()
        })
    }
}