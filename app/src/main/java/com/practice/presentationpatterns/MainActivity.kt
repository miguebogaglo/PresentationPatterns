package com.practice.presentationpatterns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practice.presentationpatterns.databinding.ActivityMainBinding
import com.practice.presentationpatterns.mvi.view.VerifyUserActivity
import com.practice.presentationpatterns.mvp.ProfileActivity
import com.practice.presentationpatterns.mvvm.RegisterActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.mvvmButton.setOnClickListener {
            startActivity(RegisterActivity.getIntent(this))
        }
        binding.mviButton.setOnClickListener {
            startActivity(VerifyUserActivity.getIntent(this))
        }
        binding.mvpButton.setOnClickListener {
            startActivity(ProfileActivity.getIntent(this))
        }
    }

}