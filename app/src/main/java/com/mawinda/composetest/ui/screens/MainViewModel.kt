package com.mawinda.composetest.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mawinda.domain.AppRepository
import com.mawinda.domain.model.Med
import com.mawinda.domain.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    val username = appRepository.username.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = ""
    )

    val greetings = appRepository.greetings.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = "Hi"
    )

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState.Idle
    )

    private val _selectedMed = MutableStateFlow<Med?>(null)
    val selectedMed = _selectedMed.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    init {
        loadMeds()
    }

    private fun loadMeds() = viewModelScope.launch {
        _uiState.value = UiState.Loading
        appRepository.loadMeds().onSuccess {
            _uiState.value = UiState.Success(it)
        }.onFailure {
            _uiState.value = UiState.Error(it.message ?: "Unknown error")
        }
    }

    fun selectMed(med: Med?) = viewModelScope.launch {
        _selectedMed.value = med
    }

    fun logout() = viewModelScope.launch {
        appRepository.logout()
    }
}