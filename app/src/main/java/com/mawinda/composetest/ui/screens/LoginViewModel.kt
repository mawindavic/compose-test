package com.mawinda.composetest.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mawinda.domain.AppRepository
import com.mawinda.domain.isValidPassword
import com.mawinda.domain.isValidUsername
import com.mawinda.domain.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState>
        get() = _uiState.asStateFlow()

    var userName by mutableStateOf("")
        private set
    var userNameError: String? by mutableStateOf(null)
        private set

    var password by mutableStateOf("")
        private set

    var passwordError: String? by mutableStateOf(null)
        private set

    var passwordIsVisible by mutableStateOf(false)
        private set


    /**
     * Updates the password field.
     * @param value The new password value.
     */
    fun updatePassword(value: String) = viewModelScope.launch {
        password = value
        passwordError = value.isValidPassword().second
        resetUiState()
    }

    /**
     * Updates the username field.
     * @param value The new username value.
     */
    fun updateUsername(value: String) = viewModelScope.launch {
        userName = value
        userNameError = value.isValidUsername().second
        resetUiState()
    }

    /**
     * Toggles the visibility of the password field.
     */
    fun togglePasswordVisibility() = viewModelScope.launch {
        passwordIsVisible = !passwordIsVisible
    }

    /**
     * Validates and logs in the user.
     *
     */
    fun validateAndLogin() = viewModelScope.launch {

        // Validate username and password
        val (isValidUsername, nameError) = userName.isValidUsername()
        userNameError = nameError

        val (isValidPassword, passError) = password.isValidPassword()
        passwordError = passError

        if (!isValidUsername || !isValidPassword) {
            _uiState.value = UiState.Error("Fix the errors in the form to proceed")
            return@launch
        }

        //Request login
        _uiState.value = UiState.Loading

        appRepository.login(userName, password)
            .onSuccess {
                _uiState.value = UiState.Success(it)

            }.onFailure {
                _uiState.value = UiState.Error(it.message ?: "Unknown error")
            }

    }


    /**
     * Clears the user inputs.
     */
    fun clearUserInputs() = viewModelScope.launch {
        userName = ""
        password = ""
        resetUiState()
    }

    /**
     * Resets the UI state to [UiState.Idle].
     */
    private fun resetUiState() = viewModelScope.launch {
        if (_uiState.value !is UiState.Idle)
            _uiState.value = UiState.Idle
    }

    /**
     * Clears the error messages.
     */
    fun clearError() = viewModelScope.launch {
        userNameError = null
        passwordError = null
        resetUiState()
    }
}