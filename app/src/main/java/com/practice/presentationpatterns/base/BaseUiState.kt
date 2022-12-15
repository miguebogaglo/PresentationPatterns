package com.practice.presentationpatterns.base


abstract class BaseUiState {
    abstract val isLoading: Boolean
    abstract val error: Int?
}