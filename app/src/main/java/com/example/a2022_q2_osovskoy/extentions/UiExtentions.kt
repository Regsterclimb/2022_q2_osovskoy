package com.example.a2022_q2_osovskoy.extentions

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
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

fun TextView.changeColor(statusState: String) = when (statusState) {
    "APPROVED" -> this.setTextColor(Color.GREEN)
    "REGISTERED" -> this.setTextColor(Color.BLUE)
    "REJECTED" -> this.setTextColor(Color.RED)
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

fun EditText.onFocusChange(hideKeyboard: () -> Unit) {
    this.setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            hideKeyboard()
        }
    }
}