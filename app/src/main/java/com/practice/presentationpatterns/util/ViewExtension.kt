package com.practice.presentationpatterns.util

import android.app.Activity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment

fun ProgressBar.show() {
    if (visibility == View.GONE)
        visibility = View.VISIBLE

}

fun ProgressBar.hide() {
   if (visibility == View.VISIBLE)
            visibility = View.GONE
}

fun Fragment.message(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun Activity.message(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}