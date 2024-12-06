package com.mawinda.composetest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mawinda.composetest.R
import com.mawinda.domain.model.UiState
import timber.log.Timber

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        UiState.Idle -> {
            Timber.i("Idle state")
        }

        UiState.Loading -> {
            Timber.i("Loading state")
            // TODO: Add loading indicator
        }

        is UiState.Error -> {
            Timber.i("Error state: ${state.message}")
            // TODO: Add Snackbar to show error
        }

        is UiState.Success<*> -> {
            val data = state.data
            Timber.d("Success state: $data")
            if (data is Boolean && data) {
                onLoginSuccess()
            }
            viewModel.clearUserInputs()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Column {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(R.string.app_logo)
            )
            Text(text = "Login Screen")
        }

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Username")

            OutlinedTextField(
                value = viewModel.userName,
                isError = !viewModel.userNameError.isNullOrEmpty(),
                onValueChange = viewModel::updateUsername,
                singleLine = true,
                supportingText = {
                    if (!viewModel.userNameError.isNullOrEmpty()) {
                        Text(viewModel.userNameError!!, style = MaterialTheme.typography.labelSmall)
                    }

                },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Password")

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = viewModel::updatePassword,
                isError = !viewModel.passwordError.isNullOrEmpty(),
                singleLine = true,
                visualTransformation = if (viewModel.passwordIsVisible) VisualTransformation.None else PasswordVisualTransformation(),
                supportingText = {
                    if (!viewModel.passwordError.isNullOrEmpty()) {
                        Text(viewModel.passwordError!!, style = MaterialTheme.typography.labelSmall)
                    }
                },
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.togglePasswordVisibility()
                    }) {
                        if (viewModel.passwordIsVisible) {
                            Icon(
                                painter = painterResource(id = R.drawable.visibility),
                                contentDescription = stringResource(id = R.string.visibility_desc)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.visibility_off),
                                contentDescription = stringResource(id = R.string.visibility_off_desc)
                            )
                        }

                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = { viewModel.validateAndLogin() }, modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Login")
            }
        }


    }

}


@Preview(showBackground = true, showSystemUi = true, name = "Login Screen")
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginSuccess = {})
}