package com.practice.presentationpatterns.mvp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.practice.presentationpatterns.R
import com.practice.presentationpatterns.base.BaseActivity
import com.practice.presentationpatterns.databinding.ActivityProfileBinding
import com.practice.presentationpatterns.util.message

class ProfileActivity : BaseActivity<ActivityProfileBinding>(), ProfileView {

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, ProfileActivity::class.java)
    }

    val profileInteract: ProfileModel = ProfileModel()
    private var presenter: ProfilePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ProfilePresenter(this, disposable, profileInteract)
        presenter?.onCreate()
    }

    override fun onStart() {
        super.onStart()
        binding.profileLogoutButton.setOnClickListener {
            presenter?.onLogoutPressed()
        }
        binding.profileDeleteAccountButton.setOnClickListener {
            showDeleteAccountAlert()
        }
        binding.profileBack.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun getLayoutRes(): Int = R.layout.activity_profile

    override fun showLoading() {
        binding.profileProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.profileProgressBar.visibility = View.GONE
    }

    override fun observeMessage() {}

    override fun observeIsLoading() {}

    private fun showDeleteAccountAlert() {
        val builder = AlertDialog.Builder(this).apply {
            setTitle("Eliminar cuenta")
            setMessage("Tu cuenta sera eliminada. Deseas continuar?")
            setPositiveButton("Eliminar") { _,_ ->
                presenter?.onDeleteAccountPressed()
            }
            setNegativeButton("Cancelar") { _,_ ->

            }
        }
        builder.create().show()
    }

    override fun setUserName(name: String) {
        binding.profileUserName.text = name
    }

    override fun isLoading(isLoading: Boolean) {
        if (isLoading)
            showLoading()
        else
            hideLoading()
    }

    override fun showMessage(message: String) {
        message(message)
    }

    override fun logout() {
        finish()
    }

    override fun deleteAccount() {
        finish()
    }

}