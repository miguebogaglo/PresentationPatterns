package com.social.photo.presentation.register

import com.practice.presentationpatterns.base.BaseUiState

data class RegisterUiState(
    val canNavigationToHome: Boolean = false,
    val canRegister: Boolean = false,
    override val isLoading: Boolean = false,
    override val error: Int? = null,
) : BaseUiState()