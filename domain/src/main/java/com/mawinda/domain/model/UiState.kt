package com.mawinda.domain.model

sealed interface UiState {
    data object Idle : UiState
    data object Loading : UiState
    data class Error(val message: String) : UiState
    data class Success<T>(val data: T) : UiState
}