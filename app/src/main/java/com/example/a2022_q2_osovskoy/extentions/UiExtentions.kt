package com.example.a2022_q2_osovskoy.extentions

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.a2022_q2_osovskoy.R
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.getTrimmedText(): String =
    this.editText?.text?.toString()?.trim().orEmpty()

fun TextInputLayout.showErrorResId(@StringRes stringResId: Int) =
    showErrorWithEnable(errorMessage = this.context.getString(stringResId))

fun TextInputLayout.showErrorWithEnable(errorMessage: String) {
    this.isErrorEnabled = true
    this.error = errorMessage
}

fun TextInputLayout.clearError() {
    this.isErrorEnabled = false
    this.error = null
}

fun TextView.changeStatus(status: String) = when (status) {
    "APPROVED" -> {
        this.setTextColor(Color.GREEN)
        this.setText(this.context.getString(R.string.approved))
    }
    "REGISTERED" -> {
        this.setTextColor(Color.BLUE)
        this.setText(this.context.getString(R.string.registered))
    }
    "REJECTED" -> {
        this.setTextColor(Color.RED)
        this.setText(this.context.getString(R.string.rejected))
    }

    else -> Unit
}

fun TextInputLayout.clearErrorOnAnyInput() =
    this.editText?.doAfterTextChanged {
        this.clearError()
    }

fun TextInputLayout.setState(changer : () ->Unit) =
    this.editText?.doAfterTextChanged {
        changer.invoke()
    }

fun View.hide() {
    this.isVisible = false
}

fun View.show() {
    this.isVisible = true
}

fun hideKeyBoard(context: Context, view: View?) {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view?.windowToken, 0)
}

fun provideOnBackPressedCallBack(navigate: () -> Unit): OnBackPressedCallback =
    object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            navigate()
        }
    }

fun TextInputLayout.onFocusChange(hideKeyboard: () -> Unit){
    this.editText?.setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            hideKeyboard()
        }
    }
}