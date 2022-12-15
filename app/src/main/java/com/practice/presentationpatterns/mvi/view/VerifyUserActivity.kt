package com.practice.presentationpatterns.mvi.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.practice.presentationpatterns.R
import com.practice.presentationpatterns.base.BaseActivity
import com.practice.presentationpatterns.databinding.ActivityVerifyUserBinding
import com.practice.presentationpatterns.mvi.VerifyUserViewModel
import com.practice.presentationpatterns.mvi.intent.VerifyUserIntent
import com.practice.presentationpatterns.mvi.model.VerifyUserState
import com.practice.presentationpatterns.util.message
import kotlinx.coroutines.launch

class VerifyUserActivity : BaseActivity<ActivityVerifyUserBinding>() {

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, VerifyUserActivity::class.java)
    }

    private val viewModel: VerifyUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeState()
        binding.verifyUserButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.intent.send(VerifyUserIntent.SendVerification)
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.activity_verify_user

    override fun showLoading() {
        binding.verifyUserProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.verifyUserProgressBar.visibility = View.GONE
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is VerifyUserState.Error -> message(state.error?.message ?: "udefined")
                    VerifyUserState.Idle -> message("Normal state")
                    VerifyUserState.Loading -> message("Loading state")
                    is VerifyUserState.SendVerification -> message("Send verification state")
                }
            }
        }

    }

    override fun observeMessage() {}

    override fun observeIsLoading() {}
}