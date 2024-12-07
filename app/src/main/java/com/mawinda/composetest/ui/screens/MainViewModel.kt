package com.mawinda.composetest.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mawinda.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    val username = appRepository.username.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = ""
    )

    val greetings = appRepository.greetings.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = "Hi"
    )


    init {
        loadProblems()
    }

    private fun loadProblems() = viewModelScope.launch {
        appRepository.loadMeds().onSuccess {
            Timber.d("Success: $it")
        }.onFailure {
            Timber.e(it)
        }

    }
}