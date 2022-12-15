package com.practice.presentationpatterns.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseStateFlowViewModel<I: BaseUiState>(T: I): ViewModel() {

    protected val _uiState = MutableStateFlow(T)
    val uiState: StateFlow<I> = _uiState

}