package com.practice.presentationpatterns.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.presentationpatterns.mvi.intent.VerifyUserIntent
import com.practice.presentationpatterns.mvi.model.VerifyUserState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class VerifyUserViewModel: ViewModel() {

    val intent = Channel<VerifyUserIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<VerifyUserState>(VerifyUserState.Idle)
    val state: StateFlow<VerifyUserState> get() = _state

    init {
        handleIntent()
    }

    private fun sendVerification() {
        viewModelScope.launch {
            _state.value = VerifyUserState.Loading
        }
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                when (it) {
                    VerifyUserIntent.SendVerification -> sendVerification()
                }
            }
        }
    }

}