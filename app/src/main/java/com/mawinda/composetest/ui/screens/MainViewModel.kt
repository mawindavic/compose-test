package com.mawinda.composetest.ui.screens

import androidx.lifecycle.ViewModel
import com.mawinda.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
}