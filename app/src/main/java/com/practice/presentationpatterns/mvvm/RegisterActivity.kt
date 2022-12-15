package com.practice.presentationpatterns.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.practice.presentationpatterns.R
import com.practice.presentationpatterns.base.BaseStateFlowActivity
import com.practice.presentationpatterns.databinding.ActivityRegisterBinding
import com.practice.presentationpatterns.util.message
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.launch

class RegisterActivity : BaseStateFlowActivity<ActivityRegisterBinding>() {

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, RegisterActivity::class.java)
    }

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeStates()
    }

    override fun onStart() {
        super.onStart()
        configureRegisterButton()
        binding.registerBack.setOnClickListener {
            onBackToLoginActivity()
        }
    }

    override fun onBackPressed() {
        onBackToLoginActivity()
    }

    private fun configureRegisterButton() {
        binding.registerButton.setOnClickListener {
            viewModel.requestRegister(
                binding.registerNameEditText.text.toString(),
                binding.registerEmailEditText.text.toString(),
                binding.registerPasswordEditText.text.toString()
            ).addTo(disposable)
        }
    }

    private fun observeStates() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    //Loading
                    if (uiState.isLoading)
                        showLoading()
                    else
                        hideLoading()
                    //Error
                    if (uiState.error != null)
                        message(getString(uiState.error))
                    //Success
                    if (uiState.canNavigationToHome)
                        navigateToHome()
                    if (uiState.canRegister)
                        viewModel.requestRegister(
                            binding.registerNameEditText.text.toString(),
                            binding.registerEmailEditText.text.toString(),
                            binding.registerPasswordEditText.text.toString()
                        ).addTo(disposable)
                }
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.activity_register

    private fun navigateToHome() {

        finish()
    }

    override fun showLoading() {
        binding.registerProgressBar.visibility = View.VISIBLE
        binding.registerButton.isEnabled = false
    }

    override fun hideLoading() {
        binding.registerProgressBar.visibility = View.GONE
        binding.registerButton.isEnabled = true
    }

    private fun onBackToLoginActivity() {

        finish()
    }

}